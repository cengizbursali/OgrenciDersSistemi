package proje.model;

public class Student extends Person {
	
	private int id;
	private int age;
	private String university;
	private int gradYear;
	
	public Student(String name, String surname, int age, String university,
			int gradYear) {
		super(name, surname);
		this.age = age;
		this.university = university;
		this.gradYear = gradYear;
	}
	
	public Student(int id,String name, String surname, int age,
			String university, int gradYear) {
		super(name, surname);
		this.id = id;
		this.age = age;
		this.university = university;
		this.gradYear = gradYear;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public int getGradYear() {
		return gradYear;
	}

	public void setGradYear(int gradYear) {
		this.gradYear = gradYear;
	}

	public String toString() {
		return "Student [id=" + id + " Name="
				+ getName() + ", Surname=" + getSurname()+ ", Age=" + age + ", University="
				+ university + ", GradYear=" + gradYear
				+"]";
	}
	
	
}
