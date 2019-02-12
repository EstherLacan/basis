package com.jiangfw.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jWX542257 on 2019/1/31.
 */
public class RandomAccessTest {

    //使用for循环遍历
    public static long traverseByLoop(List list) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    //使用迭代器遍历
    public static long traverseByIterator(List list) {
        Iterator iterator = list.iterator();
        long startTime = System.currentTimeMillis();
        while (iterator.hasNext()) {
            iterator.next();
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        //加入数据
        System.out.println("ArrayList:");
        List<String> arrayList = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 80000; i++) {
            arrayList.add("" + i);
        }
        System.out.println("ArrayList add 30000条数据时间" + (System.currentTimeMillis() - startTime));
        long loopTime = RandomAccessTest.traverseByLoop(arrayList);
        long iteratorTime = RandomAccessTest.traverseByIterator(arrayList);
        System.out.println("for循环遍历时间:" + loopTime);
        System.out.println("迭代器遍历时间:" + iteratorTime);

        List<String> linkedList = new LinkedList<>();
        //加入数据
        System.out.println("LinkedList:");
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 80000; i++) {
            linkedList.add("" + i);
        }
        System.out.println("LinkedList add 30000条数据时间" + (System.currentTimeMillis() - startTime));
        loopTime = RandomAccessTest.traverseByLoop(linkedList);
        iteratorTime = RandomAccessTest.traverseByIterator(linkedList);
        System.out.println("for循环遍历时间:" + loopTime);
        System.out.println("迭代器遍历时间:" + iteratorTime);
    }

}