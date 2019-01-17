package com.jiangfw.jvm;

/**
 * Created by jiangfw on 2018/7/26.
 */
public class TestJvm {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().maxMemory());
        System.out.println(Runtime.getRuntime().totalMemory());
    }
}
