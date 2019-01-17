package com.jiangfw.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jiangfw on 2018/7/29.
 */
public class AtomicIntergerDemo {

    static AtomicInteger i = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[10];
        for (int i = 0; i < 10; i++) {
            ts[i] = new Thread(new AddThread());
        }

        for (int i = 0; i < 10; i++) {
            ts[i].start();
        }
        //for (int i =0;i<10;i++){ts[i].join();}
        System.out.println(i + "main is end");


    }

    public static class AddThread implements Runnable {

        public void run() {
            for (int k = 0; k < 10000; k++) {
                i.incrementAndGet();
            }
        }
    }
}
