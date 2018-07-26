package com.jiangfw.primary;

import org.apache.commons.lang.StringUtils;


public class TestFindOne {

	public static void main(String[] args){
		
		StringBuilder sb=new StringBuilder();
		int n=100000000;
		for (int i = 1; i <= n; i++) {
			sb.append(i);
		}
//		System.out.println(sb.toString());
		System.out.println(StringUtils.countMatches(sb.toString(), "1"));
	}
	

}
