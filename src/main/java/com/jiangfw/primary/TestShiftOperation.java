package com.jiangfw.primary;


public class TestShiftOperation {

    public static void main(String[] args) {

        byte a22 = 5;
        int b22 = -5;
        System.out.println(Integer.toBinaryString(b22));
        System.out.println(a22 >> 2);
        System.out.println(Integer.toBinaryString(b22 >> 2));
        System.out.println(b22 >> 2);//带符号移位
        System.out.println(Integer.toBinaryString(b22 >>> 2));
        System.out.println(b22 >>> 2);//无符号移位
    }

}
