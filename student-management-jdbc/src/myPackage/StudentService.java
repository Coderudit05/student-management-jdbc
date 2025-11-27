package myPackage;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;

public class StudentService {

	public void insertStudent(Connection conn, Scanner sc) throws Exception {

		System.out.print("Enter Name: ");
		sc.nextLine();
		String name = sc.nextLine();

		System.out.print("Enter Email: ");
		String email = sc.nextLine();

		Student s = new Student(name, email);

		String insert = "INSERT INTO student(name, email) VALUES (?, ?)";

		try (PreparedStatement ps = conn.prepareStatement(insert)) {

			ps.setString(1, s.getName());
			ps.setString(2, s.getEmail());

			ps.executeUpdate();
			System.out.println("Inserted Successfully!");
			insertLog(conn, s.getName(), "INSERT");
		}

	}

	public List<Student> getAllStudents(Connection conn) throws Exception {

		List<Student> list = new ArrayList<>();

		String read = "SELECT * FROM student";

		try (PreparedStatement ps = conn.prepareStatement(read);

				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {

				// Extract data from the ResultSet
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");

				// Create Student object using constructor with id
				Student s = new Student(id, name, email);

				list.add(s);
			}
		}

		return list;
	}

	public void updateStudent(Connection conn, Scanner sc) throws Exception {

		System.out.print("Enter Student ID to Update: ");
		int id = sc.nextInt();
		sc.nextLine(); // consume newline

		System.out.print("Enter New Email: ");
		String newEmail = sc.nextLine();

		Student s = new Student();
		s.setId(id);
		s.setEmail(newEmail);

		String update = "UPDATE student SET email = ? WHERE id = ?";

		try (PreparedStatement ps = conn.prepareStatement(update)) {

			ps.setString(1, s.getEmail());
			ps.setInt(2, s.getId());

			int rows = ps.executeUpdate();

			if (rows > 0) {
				String name = getStudentById(conn, id);
				System.out.println("Updated Successfully!");
				insertLog(conn, name , "UPDATE");
			}

			else {
				System.out.println("No record found with ID " + id);
			}
		}
	}

	public void deleteStudent(Connection conn, Scanner sc) throws Exception {

		System.out.print("Enter Student ID to Delete: ");
		int id = sc.nextInt();

		Student s = new Student();
		s.setId(id);

		String delete = "DELETE FROM student WHERE id = ?";

		try (PreparedStatement ps = conn.prepareStatement(delete)) {
			ps.setInt(1, s.getId());

			int rows = ps.executeUpdate();

			if (rows > 0) {
				
				String name = getStudentById(conn,s.getId());

				System.out.println("Deleted Successfully!");
				insertLog(conn, name , "DELETE");

			} else {

				System.out.println("No record found with ID " + id);
			}
		}

	}
	
	private String getStudentById(Connection conn,int id) throws Exception{
		String name =  null;
	    String query = "SELECT name FROM student WHERE id = ?";
	    
	    try(PreparedStatement ps = conn.prepareStatement(query)){
	    	ps.setInt(1, id);
	    	ResultSet rs = ps.executeQuery();
	    	
	    	if(rs.next()) {
	    		name = rs.getString("name");
	    	}
	    }
	    return name;
	}

	public void searchStudentById(Connection conn, Scanner sc) throws Exception {

		System.out.print("Enter Student ID to Search: ");
		int id = sc.nextInt();

		Student s = new Student();
		s.setId(id);

		String query = "SELECT * FROM student WHERE id = ?";

		try (PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setInt(1, s.getId());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				int sid = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");

				Student result = new Student(sid, name, email);

				System.out.println(result.toString());

			} else {
				System.out.println("No student found with ID " + id);
			}
		}

	}

	private void insertLog(Connection conn, String studentName, String action) throws Exception {
		String logQuery = "INSERT INTO student_logs(student_name, action) VALUES (?, ?)";

		try (PreparedStatement ps = conn.prepareStatement(logQuery)) {
			ps.setString(1, studentName);
			ps.setString(2, action);

			ps.executeUpdate();
		}
	}

}
