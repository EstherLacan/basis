package com.jiangfw.test;

import java.math.BigDecimal;
import java.util.Arrays;


public class TestMain {

    public static void main(String[] args) {

        short a1 = 1;
        Short aa1 = 1;
        System.out.println(a1 == aa1.shortValue());
        BigDecimal a = new BigDecimal(0);
        BigDecimal b = new BigDecimal("0.0");
        System.out.println(b.intValue() == 0);
//		System.out.println(a.divide(b,14, BigDecimal.ROUND_CEILING));
//		System.out.println(a.divide(b,14, BigDecimal.ROUND_DOWN));
//		System.out.println(a.divide(b,14, BigDecimal.ROUND_FLOOR));
//		System.out.println(a.divide(b,14, BigDecimal.ROUND_HALF_DOWN));
//		System.out.println(a.divide(b,14, BigDecimal.ROUND_HALF_EVEN));
//		System.out.println(a.divide(b,14, BigDecimal.ROUND_HALF_UP));
////		System.out.println(a.divide(b, BigDecimal.ROUND_UNNECESSARY));
//		System.out.println(a.divide(b,14, BigDecimal.ROUND_UP));

        System.out.println((0xFFF + 1) - (1 << 12));

//		Object list = new ArrayList();
//		Object list = new String[]{"1"}; 
        Object list = new Integer[]{1};
//		Object list = new String[]{"1"}; 
//		Object list = new Short[]{1}; 
//		Object list = new Date(); 
//		Object list = new Short("1"); 

        System.out.println(list.getClass().getName());
        if (list instanceof java.util.Collection) {
            System.out.println("List is Collection!");
        } else if (list instanceof Arrays) {
            System.out.println("List is Arrays!");
        } else if (list instanceof Object[]) {
            System.out.println("List is Object[]!");
        } else {
            System.out.println("List is not Collection!");
        }
    }
}
