package com.jiangfw.common.lang.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDatabaseConnection {
	public static void main(String[] args) {
		new TestDatabaseConnection().connectSQLServer();
	}

	private void connectSQLServer() {
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL = "jdbc:sqlserver://115.239.162.30:1433;DatabaseName=trans";
		String userName = "police";
		String userPwd = "policeExtCmd";
		try {
			Class.forName(driverName);
			System.out.println("加载驱动成功！");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("加载驱动失败！");
		}
		try {
			Connection dbConn = DriverManager.getConnection(dbURL, userName,
					userPwd);
			System.out.println("连接数据库成功！");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("SQL Server连接失败！");
		}
	}
}
