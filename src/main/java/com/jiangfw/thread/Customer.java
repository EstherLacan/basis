package com.jiangfw.thread;

/**
 * Created by jiangfw on 2017/4/13.
 */
public class Customer extends Thread {

    KFC kfc;

    public Customer(String name, KFC kfc) {
        super(name);
        this.kfc = kfc;
    }

    @Override
    public void run() {
        int size = (int) (Math.random() * 5) + 5;
        while (true) {
            System.out.println("CONSUMER RUN " + this.getName());
            kfc.consu(size);
        }
    }
}
