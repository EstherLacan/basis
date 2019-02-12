package com.jiangfw.huffman;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class HuffmanTextDecoder {

    private File srcFile;         //存储经过编码的文件地址
    private HuffmanTreeNode root;
    private static final int CUR_VERSION = 1;
    private int nodesCount;

    public HuffmanTextDecoder(File src) {
        srcFile = src;
    }

    private boolean testHead(byte[] head) {
        if (head[0] == 'H' && head[1] == 'U' && head[2] == 'F'
                && head[3] == 'F') {
            if (head[4] == CUR_VERSION) {
                return true;
            }
        }
        return false;
    }

    private void parseFlatTree(byte[] ft) {             //还原HUFF树
        Queue<HuffmanTreeNode> queue = new LinkedBlockingQueue<HuffmanTreeNode>();
        int curIndex = 0;
        root = new HuffmanTreeNode();
        root.c = (char) Util.bytes2Int(ft, curIndex * 2, 2);
        queue.add(root);

        while (!queue.isEmpty()) {
            HuffmanTreeNode node = queue.poll();
            if (node.c == 0xfffe) {
                int leftIndex = curIndex + 1;
                int rightIndex = curIndex + 2;
                if (leftIndex < ft.length / 2) {
                    HuffmanTreeNode left = new HuffmanTreeNode();
                    left.c = (char) Util.bytes2Int(ft, leftIndex * 2, 2);

                    node.left = left;
                    queue.add(left);
                    curIndex++;
                }

                if (rightIndex < ft.length / 2) {
                    HuffmanTreeNode right = new HuffmanTreeNode();
                    right.c = (char) Util.bytes2Int(ft, rightIndex * 2, 2);

                    node.right = right;
                    queue.add(right);
                    curIndex++;
                }
            }
        }

    }

    private char getCharFromTree(BitReader br) {
        HuffmanTreeNode node = root;
        while (!node.isLeaf()) {
            byte bit = br.readBit();
            assert bit >= 0;
            if (bit == 1) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return node.c;                 //返回对应字符

    }

    public String decode2String() {
        return decode().toString();
    }


    private StringBuffer decode() {
        BufferedInputStream bis;
        StringBuffer sb = new StringBuffer();
        try {
            bis = new BufferedInputStream(
                    new FileInputStream(srcFile));// 创建 BufferedInputStream 并保存其参数，以便将来使用
            byte[] head = new byte[7];//先取出头7个字节进行检验
            bis.read(head);
            if (testHead(head)) {
                nodesCount = Util.bytes2Int(head, 5, 2);//把第六第七个字节转换为HUFF码表中节点长度
                byte[] codeTable = new byte[nodesCount * 2];
                Util.debug("head codeTable=" + nodesCount);//输出码表长度
                bis.read(codeTable);//读取码表
                parseFlatTree(codeTable);   //还原Huffman树
                if (root != null) {
                    byte[] data = new byte[4];
                    bis.read(data);
                    int blockSize = Util.bytes2Int(data);//读取内容部分长度
                    bis.read(data);
                    int charCount = Util.bytes2Int(data);//读取字符总数
                    int readCount = 0;
                    BitReader br = new BitReader(bis);//按位读取
                    while (readCount < charCount) {  //若没有读完，则继续读取
                        char c = getCharFromTree(br);
                        sb.append(c);
                        readCount++;
                    }

                }
            }
            bis.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            sb.delete(0, sb.length());
        }

        return sb;

    }

    public void decode2File(File dst) throws Exception {

        if (!dst.exists()) {
            dst.createNewFile();
        }

        BufferedWriter out = new BufferedWriter(new FileWriter(dst));
        StringBuffer sb = decode();
        int chars = sb.length();
        for (int i = 0; i < chars; i++) {
            char c = sb.charAt(i);
            out.write(c);
        }
        out.close();
    }

    /**
     * main.
     */
    public static void main(String[] args) throws Exception {
        HuffmanTextDecoder decoder = new HuffmanTextDecoder(new File("out.huff"));
        decoder.decode2File(new File("decoded.txt"));
//        HuffmanTextDecoder decoder = new HuffmanTextDecoder(new File("out.docx.huff"));
//        decoder.decode2File(new File("decoded.docx"));

    }

}
