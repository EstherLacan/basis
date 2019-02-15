package com.jiangfw.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Created by EstherLacan on 2019/1/18.
 */
public class TestCountDownLatch {

    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(3);
//        ExecutorService es = Executors.newFixedThreadPool(3);
//        for (int i = 0; i < 3; i++) {
//            es.submit(new Processor(cdl));
//        }

        new Thread(new Processor("Worker1", cdl, 2000)).start(); // time in millis.. 2 secs
        new Thread(new Processor("Worker2", cdl, 6000)).start();//6 secs
        new Thread(new Processor("Worker3", cdl, 4000)).start();//4 secs

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed");
    }
}


/**
 * Processor.
 */
class Processor implements Runnable {

    CountDownLatch latch;
    long workDuration;
    String name;

    public Processor(String name, CountDownLatch latch, long duration) {
        this.name = name;
        this.latch = latch;
        this.workDuration = duration;
    }


    public void run() {
        try {
            System.out.println(
                    name + " Processing Something for " + workDuration / 1000 + " Seconds");
            Thread.sleep(workDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + "completed its works");
        //when task finished.. count down the latch count...

        // basically this is same as calling lock object notify(), and object here is latch
        latch.countDown();
    }
}
