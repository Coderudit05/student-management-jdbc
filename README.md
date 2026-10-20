# Student Management System - Java JDBC

A console-based student CRUD learning project using Java, JDBC, and a MySQL database. The application demonstrates connecting Java code to a relational database and performing common student record operations.

## Features

- Insert student records
- View student records
- Update student details
- Delete student records
- Search for a student by ID
- Use JDBC prepared statements for database queries

## Tech Stack

- Java
- JDBC
- MySQL
- Eclipse project metadata is included

## Database Setup

The connection class currently expects a local MySQL database named `jdbc_db`. Create that database and a `student` table with the columns used by the Java classes before running the application.

Example schema to start from (adjust it to match the source code):

```sql
CREATE DATABASE jdbc_db;
USE jdbc_db;

CREATE TABLE student (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(255) NOT NULL
);
```

## Run the Project

1. Install a compatible JDK and MySQL server.
2. Create the database and table.
3. Configure your local database connection in the connection class.
4. Ensure the MySQL JDBC driver is available on the project's classpath.
5. Import the project into Eclipse or compile/run the Java sources using your preferred Java workflow.

## Security Note

Do not commit real database passwords to a public repository. The current connection implementation contains hard-coded local connection credentials; move credentials to environment variables or a local configuration file excluded from Git, and rotate any credential that has been used outside a disposable local database.

## Learning Goals

- Establish a JDBC connection
- Execute parameterized SQL statements
- Map query results to Java objects
- Implement CRUD operations
- Practice console-driven program flow

---

Maintained by [Udit Namdev](https://github.com/Coderudit05) as a Java and JDBC learning project.