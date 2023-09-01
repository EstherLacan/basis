package interview;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 不加启动开关会发生错乱，并不是真正按照1234线程顺序执行的？？？？？？？？？？？？？
 * <p>
 * 需要在主程序启动一个开关，后续从condition1依次开始执行
 * Thread.sleep(22000L);
 * lock.lock();
 * condition1.signal();
 * lock.unlock();
 *
 * @Author: jiangfw
 * @Date: 2020-03-18
 */
public class LockConditionTest {
    ReentrantLock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    Condition condition4 = lock.newCondition();

    char[] c1 = "12345".toCharArray();
    char[] c2 = "ABCDE".toCharArray();
    char[] c3 = "abcde".toCharArray();
    char[] c4 = "67890".toCharArray();

    @Test
    public void test() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName());
                for (char c : c1) {
                    condition1.await();
                    System.out.print(c);
                    condition2.signal();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName());
                for (char c : c2) {
                    condition2.await();
                    System.out.print(c);
                    condition3.signal();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2");

        Thread t3 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName());
                for (char c : c3) {
                    condition3.await();
                    System.out.print(c);
                    condition4.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t3");

        Thread t4 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName());
                for (char c : c4) {
                    condition4.await();
                    System.out.print(c);
                    condition1.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        Thread.sleep(1000L);
        lock.lock();
        System.out.println("***************");
        condition2.signal();//condition 1234 先唤醒谁就第一个执行
        lock.unlock();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
    }
}
