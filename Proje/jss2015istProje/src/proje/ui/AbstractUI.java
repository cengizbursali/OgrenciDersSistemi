package proje.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;

import proje.model.Course;
import proje.model.Student;


public abstract class AbstractUI {

	private final String url = "jdbc:mysql://localhost/jss2015ist"; 
	private final String user = "root";
	private final String password = "cengiz-82";
	
	protected void listStudent(Collection<Student> students){
		for (Student s : students) {
			System.out.println(s.getId()+" "+s.getName()+" "+s.getSurname()+" "+s.getAge()+" "+s.getUniversity()+" "+s.getGradYear());
		}
	}
	protected void listCourses(Collection<Course> courses)
	{
		for(Course c:courses)
		{
			System.out.println(c.getId()+" "+c.getName()+" "+c.getDescription()+" "+c.getYear()+" "+c.getLocation()+" "+c.getLecture_name()+" "+c.getLecture_surname());
		}
	}
	
	protected String showMenuAndGetSelection(Scanner scanner){
		showMenu();
		return scanner.nextLine();
	}

	protected abstract void showMenu();
	protected abstract void runMainLoop();
	
	protected Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		return DriverManager.getConnection(url, user, password);
	}
}
