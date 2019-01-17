package com.jiangfw.thread;

/**
 * Created by jiangfw on 2017/4/13.
 */
public class Waiter extends Thread {

    KFC kfc;

    public Waiter(String name, KFC kfc) {
        super(name);
        this.kfc = kfc;
    }

    @Override
    public void run() {
        int size = (int) (Math.random() * 5) + 5;
        while (true) {
            System.out.println("PRODUCER RUN " + this.getName());
            kfc.prod(size);
        }
    }
}
