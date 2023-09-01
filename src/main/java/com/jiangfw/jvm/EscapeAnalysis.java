package com.jiangfw.jvm;

/**
 * 逃逸分析
 * <p>
 * 执行参数 -XX:+PrintGC -Xms20M -Xmn20M -XX:-DoEscapeAnalysis
 *
 * @Author: jiangfw
 * @Date: 2020-03-22
 */
public class EscapeAnalysis {
    private static class Foo {
        private int x;
        private static int counter;

        public Foo() {
            x = (++counter);
        }
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        for (int i = 0; i < 1000 * 1000 * 10; ++i) {
            Foo foo = new Foo();
        }
        long end = System.nanoTime();
        System.out.println("Time cost is " + (end - start));
    }
}
