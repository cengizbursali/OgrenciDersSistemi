package proje.model;

public class Person {
	private String name;
	private String surname;//Encapsulation
	public Person(String name, String surname) {
		super();
		this.name = name;//shadowing
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	

}
