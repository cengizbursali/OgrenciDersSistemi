package proje.model;

public class Course {
	private int id;
	private String name;
	private String description;
	private int year;
	private String location;
	private String lecture_name;
	private String lecture_surname;
	private Student student;
	
	public Course(String name, String description, int year, String location,
			String lecture_name,String lecture_surname, Student student) {
		super();
		this.name = name;
		this.description = description;
		this.year = year;
		this.location = location;
		this.lecture_name = lecture_name;
		this.lecture_surname=lecture_surname;
		this.student = student;
	}
	
	
	
	public Course(int id, String name, String description, int year,
			String location, String lecture_name, String lecture_surname) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.year = year;
		this.location = location;
		this.lecture_name = lecture_name;
		this.lecture_surname = lecture_surname;
	}
	public String getLecture_surname() {
		return lecture_surname;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getLecture_name() {
		return lecture_name;
	}
	
	
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", description="
				+ description + ", year=" + year + ", location=" + location
				+ ", lecture_name=" + lecture_name +"\n"+ student
				+ "]";
	}
	
	
	
}
