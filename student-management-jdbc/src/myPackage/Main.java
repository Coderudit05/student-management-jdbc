package myPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


	public static void searchStudentById(Connection conn, Scanner sc) throws Exception {

		System.out.print("Enter Student ID to Search: ");
		int id = sc.nextInt();

		Student s = new Student();
		s.setId(id);

		String query = "SELECT * FROM student WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, s.getId());

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			// Extract data from DB
			int sid = rs.getInt("id");
			String name = rs.getString("name");
			String email = rs.getString("email");

			// Create Student object from DB data
			Student result = new Student(sid, name, email);
 
			// Print using toString()
			System.out.println(result.toString());

		} else {
			System.out.println("No student found with ID " + id);
		}
	}

	public static void main(String[] args) {
		
		StudentService service = new StudentService();


		try {

			Connection conn = DBConnection.getConnection();
			System.out.println("Connected Successfully !!!!");

			Scanner sc = new Scanner(System.in);
			int choice = 0;

			while (choice != 5) {

				System.out.println("\n==============================");
				System.out.println("     STUDENT CRUD SYSTEM      ");
				System.out.println("==============================");
				System.out.println("1. Insert Student");
				System.out.println("2. View All Students");
				System.out.println("3. Update Student");
				System.out.println("4. Delete Student");
				System.out.println("5. Search Student by ID");
				System.out.println("6. Exit");
				System.out.println("==============================");
				System.out.print("Enter your choice: ");

				choice = sc.nextInt();

				switch (choice) {

				case 1:
					service.insertStudent(conn, sc);
					break;

				case 2:
					List<Student> students = service.getAllStudents(conn);
					
					for(Student s : students) {
						System.out.println(s.toString());
					}
					break;

				case 3:
					service.updateStudent(conn, sc);
					break;

				case 4:
					service.deleteStudent(conn, sc);
					break;
				case 5:
					service.searchStudentById(conn, sc);
					break;

				case 6:
					System.out.println("Exiting Application...");
					break;

				default:
					System.out.println("Invalid Choice! Try again.");

				}

			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace(); // IMPORTANT: show error
		}
	}
}
