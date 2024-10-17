package com.DemoProject;

import java.sql.*;

public class JdbcDaoDemo {
	public static void main(String[] args) {
		StudentDAO dao = new StudentDAO();
		dao.connect();
		// fetch
		Student s1 = dao.getStudent(11);
		System.out.println(s1.name);
		// insert
		Student s2 = new Student();
		s2.rollNo = 13;
		s2.name = "sakaleshReddy";
		dao.addStudent(s2);
		// delete
		dao.removeStudent(12);
		// update
		Student s3 = new Student();
		s3.rollNo = 11;
		s3.name = "sakalesh";
		dao.updateStudent(s3);

	}
}

class StudentDAO {
	Connection con;

	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "root", "9502");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public Student getStudent(int rollNo) {
		Student s = new Student();
		s.rollNo = rollNo;
		try {
			String query = "select name from student_details where rollNo=" + rollNo;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			s.name = rs.getString(1);
		} catch (Exception e) {
			System.out.println(e);
		}
		return s;

	}

	public Student addStudent(Student student) {
		String query = "insert into student_details values(?,?)";
		PreparedStatement pt;
		try {

			pt = con.prepareStatement(query);
			pt.setInt(1, student.rollNo);
			pt.setString(2, student.name);
			int count = pt.executeUpdate();
			System.out.println(count + "rows inserted");

		} catch (Exception e) {
			System.out.println(e);
		}

		return student;

	}

	public Student removeStudent(int rollNo) {
		Student s = new Student();
		s.rollNo = rollNo;
		try {
			String query = "DELETE FROM student_details WHERE rollNo=" + rollNo;
			Statement st = con.createStatement();
			int count = st.executeUpdate(query);
			System.out.println(count + "rows deleted");
		} catch (Exception e) {
			System.out.println(e);
		}
		return s;
	}

	public Student updateStudent(Student student) {
		String query = "UPDATE student_details SET name = ? where rollNo=?";
		PreparedStatement pt;
		try {

			pt = con.prepareStatement(query);
			pt.setInt(2, student.rollNo);
			pt.setString(1, student.name);
			int count = pt.executeUpdate();
			System.out.println(count + "rows updated");

		} catch (Exception e) {
			System.out.println(e);
		}

		return student;

	}
}

class Student {
	int rollNo;
	String name;
}