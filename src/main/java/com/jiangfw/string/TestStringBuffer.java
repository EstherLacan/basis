package com.jiangfw.string;

/**
 * Created by EstherLacan on 2019/2/2.
 */
public class TestStringBuffer {

    public static void main(String[] args) {

        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.arch"));
        StringBuffer sb = new StringBuffer("111");
        sb.append("2222");
        System.out.println(sb.toString());
    }
}
