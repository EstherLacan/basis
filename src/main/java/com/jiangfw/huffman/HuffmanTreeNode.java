package com.jiangfw.huffman;

public class HuffmanTreeNode implements Comparable<HuffmanTreeNode> {

    char c;
    int freq;
    HuffmanTreeNode left;
    HuffmanTreeNode right;

    HuffmanTreeNode parent;

    public int compareTo(HuffmanTreeNode o) {
        return this.freq - o.freq;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("(c = " + c);
        sb.append(" freq = " + freq);
        sb.append(" left = " + left);
        sb.append(" right = " + right);
        sb.append(")");
        return sb.toString();

    }

    public HuffmanTreeNode() {
        c = 0xfffe;

    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}
