package com.jiangfw.huffman;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 编码格式.
 * ASCIIS码(一个ASCII码就是一个字节)：
 * 1个英文字母（不分大小写）= 1个字节的空间
 * 1个中文汉字 = 2个字节的空间
 * 1个ASCII码 = 一个字节
 *
 * UTF-8编码： 蒋(3) ※(3) あ(3) α(2) Я(2)
 * 1个英文字符 = 1个字节
 * 英文标点  = 1个字节
 * 1个中文（含繁体） = 3个字节
 * 中文标点 = 3个字节
 *
 * Unicode编码：
 * 1个英文字符 = 2个字节
 * 英文标点  = 2个字节
 * 1个中文（含繁体） = 2个字节
 * 中文标点 = 2个字节
 */
public class HuffmanTextEncoder {

    private File srcFile;        //保存原文件路径
    private File dstFile;        //保存的压缩后文件路径
    private MinHeap<HuffmanTreeNode> nodeHeap; //存储节点用的最小堆
    private HuffmanTreeNode root;              //存储Huffman树的根节点
    private Map<Character, Stack<Byte>> char2StackMap;  //Stack 表示后进先出（LIFO）的对象堆栈，建立字符与堆栈映射MAP接口
    private Map<Character, HuffmanTreeNode> char2NodeMap;//字符与节点的映射接口
    private int totalChars = 0;//内容部分字符数计数
    private int totalIndexNodeCount = 0;//码表索引节点计数

    private HuffmanTextEncoder(File src, File dst) {
        this.srcFile = src;
        this.dstFile = dst;
        char2StackMap = new HashMap<>();
    }

    private void buildHeap() throws Exception {
        char2NodeMap = new HashMap<>();
        BufferedReader in = new BufferedReader(new FileReader(srcFile));//读取原文件字符输入流
        int ch;
        //判断是否到达流末尾，若到达则返回-1，read方法作为范围 0 到 65535 (0x00-0xffff) 的整数读入的单个字符
        while ((ch = in.read()) != -1) {
            //判断（字符【键】，节点【值】）映射是否包含对于指定的字符的映射关系
            if (char2NodeMap.containsKey((char) ch)) {
                //如果已经有了即已经出现过该字符，取出该字符对应的Huffman树节点，把出现次数加一
                HuffmanTreeNode node = char2NodeMap.get((char) ch);
                node.freq++;
            } else {
                HuffmanTreeNode node = new HuffmanTreeNode();  //如果没有，则new一个Huffman树节点，并把它加入（字符【键】，节点【值】）映射
                node.c = (char) ch;
                node.freq = 1;  //第一次出现所以数值为1
                char2NodeMap.put(node.c, node); //加入映射
            }
        }
        in.close();
        nodeHeap = new MinHeap<>();
        for (Entry<Character, HuffmanTreeNode> entry : char2NodeMap  //取出（字符【键】，节点【值】）映射所包含的所有映射关系的 collection 视图
                .entrySet()) {                       //并生成迭代器
            //依次取出 映射实体
            //依次取出Huffman树节点
            HuffmanTreeNode node = entry.getValue();
            nodeHeap.add(node);   //将节点放入最小堆中 ???

            System.out.println("字符" + node.c + " 出现的次数是：" + node.freq);
        }
        //2.txt
        Collections.sort(nodeHeap.container,
                Comparator.comparingInt(n -> n.freq));
    }

    private void buildTree() {
        if (nodeHeap != null && nodeHeap.size() > 0) {
            //规则：取出频率最低的2个节点，合并成一个频率为2节点频率之和的树枝节点newNode，加入到原队列中，加入后，继续保持队列按频率升序排列.
            while (nodeHeap.size() >= 2) {
                //newNode左节点取node1与node2中出现频率少的那个节点
                HuffmanTreeNode node1 = nodeHeap.pop();
                //newNode右节点取node1与node2中出现频率少的那个节点
                HuffmanTreeNode node2 = nodeHeap.pop();
                HuffmanTreeNode newNode = new HuffmanTreeNode();
                newNode.freq = node1.freq + node2.freq;
                newNode.left = node1.compareTo(node2) > 0 ? node2 : node1;
                newNode.right = node1.compareTo(node2) > 0 ? node1 : node2;
                node1.parent = newNode;                             //设置父节点
                node2.parent = newNode;
                nodeHeap.add(newNode);//把新生成的节点放入最小堆中
                // 3.txt
                Collections.sort(nodeHeap.container,
                        Comparator.comparingInt(n -> n.freq));

            }
            root = nodeHeap.pop();  //最后一个节点即是根节点
            nodeHeap = null;
            System.gc();            //手动垃圾处理回收，快速地重用未使用对象占用的内存
        }
    }


    private void inorderTraversal(ByteArrayOutputStream baos, HuffmanTreeNode node)
            throws Exception {
        Queue<HuffmanTreeNode> queue = new LinkedBlockingQueue<>();//FIFO（先进先出）排序元素队列
        if (node != null) {
            queue.add(node);

            while (!queue.isEmpty()) {
                HuffmanTreeNode head = queue.poll();//检索并移除此队列的头

                baos.write(Util.int2Bytes(head.c, 2)); //当head为树枝节点时,值为0xfffe,否则为字符Unicode编码
                totalIndexNodeCount++; //码表索引节点计数
                if (head.left != null) {
                    queue.add(head.left);

                }

                if (head.right != null) {
                    queue.add(head.right);

                }

            }
        }
    }

    private byte[] buildHufCodeTable() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//创建一个新的字节数组输出流。
        // magic number
        baos.write('H');
        baos.write('U');
        baos.write('F');
        baos.write('F');
        // version
        baos.write(1);
        // code table length

        baos.write(Util.int2Bytes(char2NodeMap.size() * 2 - 1, 2));
        Util.debug("test  " + char2NodeMap.size());
        inorderTraversal(baos, root);//中序遍历
        Util.debug("totalIndexNodeCount = " + totalIndexNodeCount);
        baos.close();
        return baos.toByteArray(); //以字节数组的形式返回此输出流的当前内容

    }


    private Stack<Byte> getCharBits(char c) {   //返回后进先出（LIFO）的对象堆栈

        Stack<Byte> stack = char2StackMap.get(c);
        if (stack != null) {
            return (Stack<Byte>) stack.clone();
        }
        HuffmanTreeNode node = char2NodeMap.get(c);
        StringBuffer str = new StringBuffer();
        ;
        //StringBuffer s = new StringBuffer();
        if (node != null) {
            Stack<Byte> newStack = new Stack<Byte>();
            if (node == root) {
                newStack.push((byte) 1);
            } else {
                while (node.parent != null) {
                    HuffmanTreeNode parent = node.parent;

                    if (node == parent.right) {
                        newStack.push((byte) 1);
                        str.append("1");

                    } else {
                        newStack.push((byte) 0);
                        str.append("0");
                    }
                    node = parent;
                }
            }
            char2StackMap.put(c, newStack);

            str.reverse();
            System.out.println("字符" + c + "的编码是：" + str);
            return (Stack<Byte>) newStack.clone();
        } else {
            assert 1 == 0;
            return null;
        }


    }

    private byte[] getContentBytes() throws Exception {
        BitWriter bw = new BitWriter(128);
        BufferedReader in = new BufferedReader(new FileReader(srcFile));//读取源文件
        int ch = -1;
        totalChars = 0;//内容部分字符数计数
        //读取一个字符
        while ((ch = in.read()) != -1) {
            Stack<Byte> bitsStack = getCharBits((char) ch);
            if (bitsStack != null) {
                totalChars++;//内容部分字符数加一
                while (!bitsStack.isEmpty()) {
                    byte b = bitsStack.pop();
                    bw.write(b, 1);
                }
            }
        }
        in.close();
        return bw.toByteArray();


    }


    /**
     * 压缩主方法.
     */
    public void encode() throws Exception {
        buildHeap();
        buildTree();
        if (dstFile.exists()) {
            dstFile.delete();
        }

        byte[] head = buildHufCodeTable();
        FileOutputStream fos = new FileOutputStream(dstFile);//创建一个向dstFile文件中写入数据的文件输出流
        DataOutputStream dos = new DataOutputStream(fos);//创建一个新的数据输出流dos，将数据写入fos基础输出流
        dos.write(head);//写入Huffman码表
        Util.debug("head len=" + head.length);//输出码表长度
        byte[] content = getContentBytes();//取得内容部分编码
        Util.debug("content len=" + content.length);//内容部分字节长度
        dos.write(Util.int2Bytes(content.length, 4));//写入内容部分长度
        dos.write(Util.int2Bytes(totalChars, 4));//写入字符总数
        dos.write(content);//写入编码后的内容
        //reserved
        dos.write(Util.int2Bytes(0, 4));//循环冗余校验码（CRC） 预留字段
        dos.close();
        fos.close();
    }

    /**
     * main.
     */
    public static void main(String[] args) throws Exception {
        File src = new File("encoder.txt");
        File dst = new File("out.huff");
//        File src = new File("encoder.docx");
//        File dst = new File("out.docx.huff");
        HuffmanTextEncoder encoder = new HuffmanTextEncoder(src, dst);
        encoder.encode();

    }


}