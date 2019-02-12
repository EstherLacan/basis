package com.jiangfw.queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 了解了优先队列之后，我们再来看它的一个应用：
 * 在面试的时候，问到算法，Top k 的问题是经常被问到的，网上已有很多种方法可以解决，
 * 今天来看看如何使用 PriorityQueue 构造固定容量的优先队列，模拟大顶堆，来解决 top K 小的问题。
 * https://my.oschina.net/leejun2005/blog/135085
 */
public class TopKQueue<E extends Comparable> {

    public int topNum = 10;

    public PriorityQueue<E> queue;

    public Comparator<E> comparator;//按顺序表示top K最小，逆序表示topk最大。

    public TopKQueue(int topNum, Comparator<E> comparator) {
        if (topNum <= 0) {
            throw new IllegalArgumentException();
        }
        this.topNum = topNum;
        this.comparator = comparator;
        this.queue = new PriorityQueue<>(this.topNum, this.comparator);
    }

    public void add(E e) {
        if (this.queue.size() < topNum) {
            this.queue.add(e);
        } else {
            E element = this.queue.peek();
            // 如果comparator正序，怎加进来的数必须比当前最小的数要大（topk最大）；否则要比最大的数要小（topk最小）
            if (this.comparator.compare(e, element) > 0) {
                this.queue.poll();
                this.queue.add(e);
            }
        }
    }

    public static void main(String[] args) {
        // 正序表示处理topk的最大数
        Comparator<Integer> topKMax = Comparator.comparingInt(a -> a);
        // 逆序表示最小数。
        Comparator<Integer> topKMin = (a, b) -> b - a;

        TopKQueue<Integer> top = new TopKQueue<>(5, topKMin);
        System.out.println("100 个 0~999 之间的随机数：-----------------------------------");
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            Integer rNum = random.nextInt(1000);
            System.out.println(rNum);
            top.add(rNum);
        }

        System.out.println("PriorityQueue 本身的遍历是无序的：-----------------------------------");
        Iterable<Integer> iter = () -> top.queue.iterator();
        for (Integer item : iter) {
            System.out.print(item + ", ");
        }
        System.out.println();
        System.out.println("PriorityQueue 排序后的遍历：-----------------------------------");
        while (!top.queue.isEmpty()) {
            System.out.print(top.queue.poll() + ",");
        }
    }
}
