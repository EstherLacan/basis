package com.jiangfw.common.lang.test.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestNet {

	/**
	 *<p>Description:</p>
	 *
	 * @param args
	 *
	 * @author jiangfengwei
	 *
	 */
	public static void main(String[] args) {
		 long time = 0;
         // 得到本机名
         InetAddress address1;
		try {
			address1 = InetAddress.getLocalHost();
		
         System.out.println("本机名： " + address1.getHostName());
         // 直接返回域名
         InetAddress address2 = InetAddress.getByName("www.ccfsoft.com");
         time = System.currentTimeMillis();
         System.out.print("直接得到域名： " + address2.getHostName());
         System.out.println("所用时间："
                         + String.valueOf(System.currentTimeMillis() - time) + " 毫秒");
         // 通过DNS查找域名
         InetAddress address3 = InetAddress.getByName("192.168.1.1");
         System.out.println("address3:    " + address3);    // 域名为空
         time = System.currentTimeMillis();
         System.out.print("通过DNS查找域名： " + address3.getHostName());
         System.out.println("所用时间：" + String.valueOf(System.currentTimeMillis() - time)+ " 毫秒");
         System.out.println("address3:    " + address3);    // 同时输出域名和IP地址
         } catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
