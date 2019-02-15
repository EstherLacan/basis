package com.jiangfw.generics;

/**
 * 使用泛型的上界 extends 和下界 supers.
 * Created by EstherLacan on 2019/1/25.
 */
public class TestGenerics {

    public static void main(String[] args) {

//        创建实例的时候不要这样用上界和下届
//        Plate<? extends Fruit> p = new Plate<>(new Apple());
//
////不能存入任何元素
//        p.set(new Fruit());    //Error
//        p.set(new Apple());    //Error
//
////读取出来的东西只能存放在Fruit或它的基类里。
//        Fruit newFruit1 = p.get();
//        Object newFruit2 = p.get();
//        Apple newFruit3 = p.get();    //Error

        Plate<Fruit> p2 = new Plate<>(new Apple());
//不能存入任何元素
        p2.set(new Fruit());
        p2.set(new Apple());
//读取出来的东西只能存放在Fruit或它的基类里。
        Fruit newFruit12 = p2.get();
        Object newFruit22 = p2.get();
        Apple newFruit32 = p2.getExtends();    //Error

        System.out.println(newFruit12);
        System.out.println(newFruit22);
        System.out.println(newFruit32);
    }
}
