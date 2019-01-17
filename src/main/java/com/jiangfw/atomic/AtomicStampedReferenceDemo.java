package com.jiangfw.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by jiangfw on 2018/7/29.
 */
public class AtomicStampedReferenceDemo {

    public static final AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(
            19, 0);

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        final int timestamp = money.getStamp();
                        while (true) {
                            Integer m = money.getReference();
                            if (m < 20) {
                                if (money.compareAndSet(m, m + 20, timestamp, timestamp + 1)) {
                                    System.out.println("余额小于20元，充值成功，余额为" + money.getReference());
                                    break;
                                } else {
                                    //System.out.println("充值失败");
                                }
                                break;
                            } else {
                                // try {
                                //     Thread.sleep(1000);
                                // } catch (InterruptedException e) {
                                //     e.printStackTrace();
                                // }
                                break;
                            }
                        }
                    }
                }
            }.start();
        }

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    while (true) {
                        int timestamp = money.getStamp();
                        Integer m = money.getReference();
                        if (m > 10) {
                            System.out.println("大于10元");
                            if (money.compareAndSet(m, m - 10, timestamp, timestamp + 1)) {
                                System.out.println("消费10元，余额:" + money.getReference());
                                break;
                            } else {
                                System.out.println("消费失败");
                                break;
                            }
                        } else {
                            System.out.println("没有足够的余额");

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            break;
                        }


                    }
                }
            }
        }.start();
    }
}
