package myPackage;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection getConnection() {

		Connection conn = null;

		try {
			String url = System.getenv().getOrDefault(
					"DB_URL", "jdbc:mysql://localhost:3306/jdbc_db");
			String user = System.getenv("DB_USER");
			String pass = System.getenv("DB_PASSWORD");

			if (user == null || user.isBlank() || pass == null || pass.isBlank()) {
				throw new IllegalStateException(
						"Set DB_USER and DB_PASSWORD environment variables before connecting.");
			}

			conn = DriverManager.getConnection(url, user, pass);
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}
}
