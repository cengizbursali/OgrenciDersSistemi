package proje.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;


import proje.model.Student;;

public class StudentRepository extends AbstractRepository{
	private final PreparedStatement getAllStatement;
	private final PreparedStatement saveStatement;

	public StudentRepository(Connection connection) throws SQLException{
		super(connection);
		getAllStatement = connection.prepareStatement("SELECT * FROM students");
		saveStatement = connection.prepareStatement("INSERT INTO "
				+ "students(id,name, surname, age,university,gradyear) "
				+ "VALUES (?,?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
	}
	public Collection<Student> getAll(){
		try {
			ResultSet rs = getAllStatement.executeQuery();
			Collection<Student> result = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				int age = rs.getInt("age");
				String university = rs.getString("university");
				int gradyear = rs.getInt("gradyear");
				Student student = new Student(id,name,surname,age,university,gradyear);
				result.add(student);
			}
			return result;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Student saveStudent(Student student){
		try {
			saveStatement.setInt(1, student.getId());
			saveStatement.setString(2, student.getName());
			saveStatement.setString(3, student.getSurname());
			saveStatement.setInt(4, student.getAge());
			saveStatement.setString(5, student.getUniversity());
			saveStatement.setInt(6, student.getGradYear());
			
			
			saveStatement.executeUpdate();
			
			ResultSet generatedKeys = saveStatement.getGeneratedKeys();
			Student result = null;
			while (generatedKeys.next()) {
				int id = generatedKeys.getInt(1);
				result = new Student(id,student.getName(), 
						student.getSurname(),student.getAge(),student.getUniversity(),student.getGradYear());
			}
			return result;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
