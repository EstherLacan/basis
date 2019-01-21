package com.jiangfw.alibaba.protocol;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 《阿里巴巴Java开发手册(终极版).pdf》.
 * 编程规约->(六)并发处理->5.【强制】SimpleDateFormat 是线程不安全的类，一般不要定义为static变量，如果定义为static，必须加锁，或者使用DateUtils工具类。
 * https://www.toutiao.com/a6640810900565525006
 *
 * @Author: jiangfw
 * @date: 2019/1/18.
 */
public class TestSimpleDateFormat {

    /**
     * 定义一个全局的SimpleDateFormat.
     */
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 使用ThreadFactoryBuilder定义一个线程池.
     */
    private static ThreadFactory namedThreadFactory =
            new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();

    private static ExecutorService pool =
            new ThreadPoolExecutor(
                    50,
                    200,
                    0L,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>(1024),
                    namedThreadFactory,
                    new ThreadPoolExecutor.AbortPolicy());
    /**
     * 定义一个CountDownLatch，保证所有子线程执行完之后主线程再执行.
     */
    private static CountDownLatch countDownLatch = new CountDownLatch(100);

    /**
     * main.
     *
     * @param args arguments
     * @throws InterruptedException InterruptedException.
     */
    public static void main(String[] args) throws InterruptedException {

        final long awaitTime = 5 * 1000;
        // 定义一个线程安全的HashSet
        Set<String> dates = Collections.synchronizedSet(new HashSet<String>());
        for (int i = 0; i < 100; i++) {
            // 获取当前时间
            Calendar calendar = Calendar.getInstance();
            int finalI = i;
            pool.execute(
                    () -> {
                        // 时间增加
                        calendar.add(Calendar.DATE, finalI);
                        // 通过simpleDateFormat把时间转换成字符串
                        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String dateString = simpleDateFormat.format(calendar.getTime());
                        // 把字符串放入Set中
                        dates.add(dateString);
                        System.out.println(Thread.currentThread().getName());
                        // countDown
                        countDownLatch.countDown();
                    });
        }
        // 阻塞，直到countDown数量为0
        countDownLatch.await();
        // 输出去重后的时间个数
        System.out.println(dates.size());

        try {
            pool.shutdown();

            if (!pool.awaitTermination(awaitTime, TimeUnit.MILLISECONDS)) {
                // 超时的时候向线程池中所有的线程发出中断(interrupted)。
                pool.shutdownNow();
            }
        } catch (InterruptedException e) {
            // awaitTermination方法被中断的时候也中止线程池中全部的线程的执行。
            System.out.println("awaitTermination interrupted: " + e);
            pool.shutdownNow();
        }
    }
}
