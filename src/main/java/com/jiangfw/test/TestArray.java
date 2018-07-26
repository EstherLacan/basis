package com.jiangfw.test;

import org.apache.commons.lang.ArrayUtils;


public class TestArray {
	
	public static void main(String[] args) {
		
		int[] aa=null;
		if (ArrayUtils.isNotEmpty(aa)) {
			System.out.println("aaa");
		}
		System.out.println(aa);
		String a=ArrayUtils.toString(aa, "qasdwwwwwwwwwwwwww");
		System.out.println(a);
		System.out.println(a.substring(1, a.length()-1));
	}

}
