package com.jiangfw.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangfw on 2017/4/13.
 */
public class KFC {

    static final int MAX = 20;
    String[] names = {"薯条", "烧板", "鸡翅", "可乐", "汉堡"};
    List<Food> foods = new ArrayList<>();

    public void consu(int size) {
        synchronized (this) {

            String name = Thread.currentThread().getName();
            System.out.println("customer is " + name);
            while (foods.size() < size) {
                System.out.println(
                        "foods size is " + foods.size() + ",consume " + size + ",food is leak...");
                this.notifyAll();
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("customer " + name + " start");
            for (int i = 0; i < size; i++) {
                Food food = foods.remove(foods.size() - 1);
                System.out.println("customer " + name + " consume one food:" + food.getName()
                        + ",foods size is " + foods.size());
            }
        }
    }

    public void prod(int size) {
        synchronized (this) {
            String name = Thread.currentThread().getName();
            System.out.println("producer is " + name);
            while (foods.size() > MAX) {
                System.out.println("foods size is " + foods.size() + ", food is enough...");
                this.notifyAll();
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("producer " + name + " start");
            for (int i = 0; i < size; i++) {
                Food food = new Food(names[(int) (Math.random() * 5)]);
                this.foods.add(food);
                System.out.println("producer " + name + " produces one food :" + food.getName()
                        + ",foods size is " + foods.size());
            }
        }
    }
}
