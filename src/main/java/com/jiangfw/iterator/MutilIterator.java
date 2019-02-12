package com.jiangfw.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * 上个代码，直观地展示一下实现了Iterable的类如何通过返回不同的Iterator从而实现不同的遍历方式.
 * MutilIterator实现了三种迭代器，
 * 分别是默认的前向迭代器，反向迭代器和随机迭代器。主函数中分别调用了三种迭代器进行遍历。
 */
public class MutilIterator implements Iterable<String> {

    private String[] words = "May I get offers this summer.".split(" ");

    //默认的迭代器，前向遍历
    public Iterator<String> iterator() {
        //匿名内部类
        return new Iterator<String>() {
            private int index = 0;

            public boolean hasNext() {
                return index < words.length;
            }

            public String next() {
                return words[index++];
            }

            public void remove() { // Not implemented
                throw new UnsupportedOperationException();
            }
        };
    }

    //反向迭代器
    public Iterable<String> reverseIterator() {
        return () -> new Iterator<String>() {
            private int index = words.length - 1;

            public boolean hasNext() {
                return index > -1;
            }

            public String next() {
                return words[index--];
            }

            public void remove() { // Not implemented
                throw new UnsupportedOperationException();
            }
        };
    }

    //随机迭代器，注意这里不是创建一个新的Iterator，而是返回了一个打乱的List中的迭代器
    public Iterable<String> randomized() {
        return new Iterable<String>() {
            public Iterator<String> iterator() {
                List<String> shuffled = new ArrayList<>(Arrays.asList(words));
                Collections.shuffle(shuffled, new Random(47));
                return shuffled.iterator();
            }
        };
    }

    public static void main(String[] args) {
        MutilIterator mi = new MutilIterator();
        //默认的迭代器
        for (String String : mi) {
            System.out.print(String + " ");
        }
        System.out.println();
        //反向迭代器
        for (String String : mi.reverseIterator()) {
            System.out.print(String + " ");
        }
        System.out.println();
        //随机迭代器
        for (String String : mi.randomized()) {
            System.out.print(String + " ");
        }
        System.out.println();
    }
}