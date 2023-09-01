package interview;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用两个线程交替打印出1A2B3C4D5E6F7G
 * 一个线程打印数字
 * 一个线程打印字母
 *
 * @Author: jiangfw
 * @Date: 2020-03-07
 */
public class PrintAlphaNumber {
    static Thread t1, t2 = null;

    public static void main(String[] args) {
        char[] cI = "1234567".toCharArray();
        char[] cC = "ABCDEFG".toCharArray();
        print6(cI, cC);

    }


    /**
     * LockSupport 通信
     *
     * @param cI
     * @param cC
     */
    public static void print1(char[] cI, char[] cC) {
        t1 = new Thread(() -> {
            for (char c : cI) {
                System.out.print(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "t1");


        t2 = new Thread(() -> {
            for (char c : cC) {
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);
            }
        }, "t2");

        t1.start();
        t2.start();
    }

    public static volatile ThreadState r = ThreadState.T1;

    /**
     * 自旋通信
     *
     * @param cI
     * @param cC
     */
    public static void print2(char[] cI, char[] cC) {

        new Thread(() -> {
            for (char c : cI) {
                while (r != ThreadState.T1) {
                }
                System.out.print(c);
                r = ThreadState.T2;
            }
        }, "t1").start();


        new Thread(() -> {
            for (char c : cC) {
                while (r != ThreadState.T2) {
                }
                System.out.print(c);
                r = ThreadState.T1;
            }
        }, "t2").start();
    }


    enum ThreadState {
        T1, T2;
    }

    static BlockingQueue<String> q1 = new ArrayBlockingQueue<>(1);
    static BlockingQueue<String> q2 = new ArrayBlockingQueue<>(1);

    /**
     * 通过阻塞队列通信
     *
     * @param cI
     * @param cC
     */
    public static void print3(char[] cI, char[] cC) {

        new Thread(() -> {
            for (char c : cI) {
                System.out.print(c);
                try {
                    q1.put("ok");
                    q2.take();
                } catch (InterruptedException e) {

                }
            }
        }, "t1").start();


        new Thread(() -> {
            for (char c : cC) {
                try {
                    q1.take();
                    System.out.print(c);
                    q2.put("ok");
                } catch (InterruptedException e) {

                }
            }
        }, "t2").start();
    }


    /**
     * 管道流的阻塞进行通信
     * 效率极低。
     *
     * @param cI
     * @param cC
     */
    public static void print4(char[] cI, char[] cC) {
        PipedInputStream input1 = new PipedInputStream();
        PipedInputStream input2 = new PipedInputStream();
        PipedOutputStream output1 = new PipedOutputStream();
        PipedOutputStream output2 = new PipedOutputStream();

        try {
            input1.connect(output2);
            input2.connect(output1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String msg = "your turn";


        new Thread(() -> {
            byte[] buffer = new byte[9];

            for (char c : cI) {
                System.out.print(c);
                try {

                    output1.write(msg.getBytes());
                    input1.read(buffer);
//                    if (new String(buffer).equalsIgnoreCase(msg)) {
//                        continue;
//                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();


        new Thread(() -> {
            byte[] buffer = new byte[9];
            for (char c : cC) {
                try {
                    input2.read(buffer);
                    if (new String(buffer).equalsIgnoreCase(msg)) {
                        System.out.print(c);
                    }
                    output2.write(msg.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();
    }


    //控制哪个线程先执行的标记符
    static boolean t1first = false;

    /**
     * notify 和 wait 以及synchronized联合使用
     *
     * @param cI
     * @param cC
     */
    public static void print5(char[] cI, char[] cC) {

        final Object o = new Object();
        new Thread(() -> {

            synchronized (o) {
                if (!t1first) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (char c : cI) {
                    System.out.print(c);
                    t1first = !t1first;
                    try {
                        o.notify();//唤醒其他锁(因为wait而睡眠的线程)
                        o.wait();//让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();//唤醒其他锁
            }
        }, "t1").start();


        new Thread(() -> {
            synchronized (o) {
                if (t1first) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (char c : cC) {
                    System.out.print(c);
                    t1first = !t1first;
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();//唤醒其他锁
            }
        }, "t2").start();
    }


    public static void print6(char[] cI, char[] cC) {
        Lock lock = new ReentrantLock();

        //condition 可以精准控制哪个线程获取锁，
        //这个比o.notify 的好处就是notify一个锁但是那个锁是有线程唤醒机制随机唤醒的,并不知道到底是哪个
        //所以在多个线程竞争一个对象锁的时候，用condition比较好
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        new Thread(() -> {
            try {
                lock.lock();
                for (char c : cI) {
                    System.out.print(c);
                    condition2.signal();
                    condition1.await();

                }
                condition2.signal(); //类似于唤醒最后一个condition.wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("t1^^^^^^^^^^^^^^^^^^^^");
                lock.unlock();
            }
        }, "t1").start();


        new Thread(() -> {
            try {
                lock.lock();
                for (char c : cC) {
                    System.out.print(c);
                    condition1.signal();
                    condition2.await();
                }
                condition1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("t2^^^^^^^^^^^^^^^^^^^^");
                lock.unlock();
            }
        }, "t2").start();
    }
}
