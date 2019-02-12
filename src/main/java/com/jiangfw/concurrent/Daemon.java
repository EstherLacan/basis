package com.jiangfw.concurrent;


/**
 * 在Java虚拟机退出的时候，finally块不一定会执行
 */
public class Daemon {

    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
        //设置该线程为守护线程
        thread.setDaemon(true);
        thread.start();

    }

    static class DaemonRunner implements Runnable {

        public void run() {
            try {
                SleepUtils.second(10);
            } finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }
}
