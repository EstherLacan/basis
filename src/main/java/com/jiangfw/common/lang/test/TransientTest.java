package com.jiangfw.common.lang.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class TransientTest implements Serializable {

	/**
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param args
	 * 
	 * @author jiangfengwei
	 * 
	 */
	public static void main(String[] args) {

		TransientTest a = new TransientTest("Hulk", "myLittlePony");
		System.out.println("logon a = " + a);
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(
					"Logon.out"));
			o.writeObject(a);
			o.close();
			// Delay:
			int seconds = 5;
			long t = System.currentTimeMillis() + seconds * 1000;
			while (System.currentTimeMillis() < t);
			// Now get them back:
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					"Logon.out"));
			System.out.println("Recovering object at " + new Date());
			a = (TransientTest) in.readObject();
			System.out.println("logon a = " + a);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Date date = new Date();
	private String username;
	private transient String password;

	TransientTest(String name, String pwd) {
		username = name;
		password = pwd;
	}

	public String toString() {
		String pwd = (password == null) ? "(n/a)" : password;
		return "logon info: \n " + "username: " + username + "\n date: "
				+ date.toString() + "\n password: " + pwd;
	}
}
