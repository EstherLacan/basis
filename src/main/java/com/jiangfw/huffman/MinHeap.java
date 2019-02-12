package com.jiangfw.huffman;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MinHeap<T> {

    public List<T> container;

    public MinHeap() {
        container = new LinkedList<>();
    }

//    private int getTargetIndex(Comparable<T> e) {  //按规则生成传入模板类的最小堆所在位置
//        int index1 = 0;
//        int index2 = container.size() - 1;
//        int mid;
//
//        while (index1 <= index2) {
//            mid = (index1 + index2) / 2;
//            Comparable<T> midt = container.get(mid);
//            int cv = e.compareTo((T) midt);
//            if (cv > 0) {
//                index1 = mid + 1;
//            } else if (cv < 0) {
//                index2 = mid - 1;
//            } else {
//                break;
//            }
//        }
//
//        return index1;
//
//    }

    public void add(T e) {
        container.add(e);//在此列表中指定的位置插入指定的元素
    }

    public T pop() {
        if (container.isEmpty()) {
            return null;
        } else {
            return container.remove(0);  //删除第一个元素并返回该元素
        }
    }

    public int size() {
        return container.size();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (Iterator<T> iterator = container.iterator(); iterator
                .hasNext(); ) {
            T item = iterator.next();
            sb.append(item.toString() + ",");
        }
        int len = sb.length();
        if (sb.charAt(len - 1) == ',') {
            sb.deleteCharAt(len - 1);
            sb.append(']');
        }
        return sb.toString();

    }

}