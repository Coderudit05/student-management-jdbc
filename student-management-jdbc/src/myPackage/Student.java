package myPackage;

public class Student {

	private int id;
	private String name;
	private String email;

	public Student() {
	} // no args constructor not for now only for future uses

	// Constructor for creating a new student (without id)
	public Student(String name, String email) {
		this.name = name;
		this.email = email;

	}

	// Constructor with id (when reading from DB)
	public Student(int id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// Optional: nice toString for easy printing
	@Override
	public String toString() {
		return id + " " + name + " " + email;
	}

}
