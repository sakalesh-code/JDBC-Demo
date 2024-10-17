package com.DemoProject;

import java.sql.*;

//1. import package ---> java.mysql 
//2. load and register driver ---> com.mysql.jdbc.Driver
//3. connection ---> connection interface 
//4. create a statement  ---> statement
//5. execute the query 
//6. process the results 
//7. close

public class Demo {
	public static void main(String[] args) throws Exception {
		Demo1 demo = new Demo1();
		// demo.fetch();
		// demo.insert();
		// demo.insert2();
		demo.insert3();

	}
}

class Demo1 {
	public void fetch() throws Exception {

		String url = "jdbc:mysql://localhost:3306/practice";
		String user = "root";
		String password = "9502";
		String query = "select * from student";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);

		while (rs.next()) {
			String name = rs.getString("user_Name");
			int id = rs.getInt("user_id");
			System.out.println(id + " : " + name);
		}
//		while(rs.next()) {
//			String id = rs.getString(1);
//			String name = rs.getString(2);
//			
//			System.out.println(id +" : "+name);
//		}

		st.close();
		con.close();

	}

	public void insert() throws Exception {

		String url = "jdbc:mysql://localhost:3306/practice";
		String user = "root";
		String password = "9502";
		String query = "insert into student values(4,'sakhi')";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st = con.createStatement();
		int count = st.executeUpdate(query);
		System.out.println(count + " rows Updated");

		st.close();
		con.close();

	}

	public void insert2() throws Exception {

		String url = "jdbc:mysql://localhost:3306/practice";
		String user = "root";
		String password = "9502";
		int userId = 5;
		String userName = "rakesh";
		String query = "insert into student values(" + userId + ",'" + userName + "')";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st = con.createStatement();
		int count = st.executeUpdate(query);
		System.out.println(count + " rows Updated");

		st.close();
		con.close();

	}

	public void insert3() throws Exception {

		String url = "jdbc:mysql://localhost:3306/practice";
		String user = "root";
		String password = "9502";
		int userId = 8;
		String userName = "ak";
		String query = "insert into student values(?,?)";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, password);
		PreparedStatement st = con.prepareStatement(query);// PreparedStatement
		st.setInt(1, userId);
		st.setString(2, userName);
		int count = st.executeUpdate();// DDL,DML,DQL
		System.out.println(count + " rows Updated");

		st.close();
		con.close();

	}
}
