package proje.ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;


import proje.db.CourseRepository;
import proje.db.StudentRepository;
import proje.model.Course;
import proje.model.Student;

public class Client extends AbstractUI {
	private final StudentRepository stuRepository;
	private final CourseRepository couRepository;

	public Client() {
		try {
			Connection connection = getConnection();
			stuRepository = new StudentRepository(connection);
			couRepository=new CourseRepository(connection,stuRepository);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static void main(String[] args) {
		Client ui = new Client();
		ui.runMainLoop();
	}

	@Override
	protected void showMenu() {
		System.out.println();
		System.out.println("Please make a selection:");
		System.out.println("1. List students");
		System.out.println("2. List courses");
		System.out.println("3. Add students");
		System.out.println("4. Add courses");
		System.out.println("q. Quit");

	}

	@Override
	protected void runMainLoop() {
		Scanner scanner = new Scanner(System.in);
		String line = "";
		Collection<Student> students = stuRepository.getAll();
		Collection<Course> courses= couRepository.getAll();
		while (!(line = showMenuAndGetSelection(scanner)).equals("q")) {
			int selection = Integer.valueOf(line);
			switch (selection) {
			case 1:
				listStudent(students);
				break;
			case 2:
				listCourses(courses);
				break;
			case 3:
				addStudent(scanner,students); 
				System.out.println("Student is added");
				break;
			case 4:
				addCourse(scanner, courses);
				System.out.println("Course is added");
				break;
			
			default:
				System.out.println("Invalid choice, please try again.");
				break;
			}
		}

	}
	private void addStudent(Scanner scanner, Collection<Student> students){
		System.out.println("Enter a student in the following format:");
		System.out.println("name, surname, age,university,gradyear");
		Random random=new Random();
		int id = 1000 + random.nextInt(150);
		String line = scanner.nextLine();
		String[] splitted = line.split(",");
		String name = splitted[0];
		String surname=splitted[1];
		Integer age = Integer.valueOf(splitted[2]);
		String university=splitted[3];
		Integer gradyear = Integer.valueOf(splitted[4]);
		Student student = new Student(id,name, surname, age,university,gradyear);
		student = stuRepository.saveStudent(student);
		students.add(student);
	}
	private void addCourse(Scanner scanner, Collection<Course> courses)
	{
		System.out.println("Enter a course in the following format:");
		System.out.println("name, description, year,location");
		String line = scanner.nextLine();
		String[] splitted = line.split(",");
		Random random=new Random();
		int id = 5 + random.nextInt(150);
		String name = splitted[0];
		String description=splitted[1];
		Integer year = Integer.valueOf(splitted[2]);
		String location=splitted[3];
		System.out.println("Enter a person for course in the following format");
		System.out.println("name, surname");
		String line2="";
		line2=scanner.nextLine();
		String[] splitted2=line2.split(",");
		String lect_name = splitted2[0];
		String lect_surname = splitted2[1];
		Course course=new Course(id, name, description, year, location, lect_name, lect_surname);
		course=couRepository.saveCourse(course);
		courses.add(course);
		
	}

}
