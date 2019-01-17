package com.jiangfw.thread;

/**
 * Created by jiangfw on 2017/4/13.
 */
public class KFCTest {

    public static void main(String[] args) {
        KFC kfc = new KFC();

        Customer c1 = new Customer("c1", kfc);
        Customer c2 = new Customer("c2", kfc);
        Customer c3 = new Customer("c3", kfc);
        Customer c4 = new Customer("c4", kfc);

        Waiter w1 = new Waiter("w1", kfc);
        Waiter w2 = new Waiter("w2", kfc);
        Waiter w3 = new Waiter("w3", kfc);

        w1.start();
        w2.start();
        w3.start();

        c1.start();
        c2.start();
        c3.start();
        c4.start();
    }
}
