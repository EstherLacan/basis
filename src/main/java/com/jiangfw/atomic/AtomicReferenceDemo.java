package com.jiangfw.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by jiangfw on 2018/7/29.
 */
public class AtomicReferenceDemo {

    public static final AtomicReference<String> atomicstr = new AtomicReference<String>("abc");


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int num = 1;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(Math.abs((int) (Math.random() * 100)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (atomicstr.compareAndSet("abc", "def")) {
                        System.out.println(
                                "Thread:" + Thread.currentThread().getId() + "change value def");
                    } else {
                        System.out.println(
                                "Thread:" + Thread.currentThread().getId() + "change value failed");
                    }
                }
            }.start();
        }
    }
}
