package myPackage;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection getConnection() {

		Connection conn = null;

		try {
			String url = "jdbc:mysql://localhost:3306/jdbc_db";
			String user = "root";
			String pass = "1234";

			conn = DriverManager.getConnection(url, user, pass);
		}

		catch (Exception e) {

			e.printStackTrace();
		}

		return conn;
	}
}
