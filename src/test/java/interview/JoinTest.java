package interview;

import java.util.concurrent.TimeUnit;

/**
 * @Author: jiangfw
 * @Date: 2020-03-19
 */
public class JoinTest {
    public static void main(String[] args) throws Exception {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            // 每个线程拥有前一个线程的引用，需要等待前一个线程终止，才能从等待中返回
            Thread thread = new Thread(new Runner(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }

    static class Runner implements Runnable {
        private Thread previous;

        public Runner(Thread thread) {
            this.previous = thread;
        }

        public void run() {
            try {
                previous.join();
            } catch (InterruptedException ignored) {
            }
            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }
}
