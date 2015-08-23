package proje.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import proje.model.Course;
import proje.model.Student;

public class CourseRepository extends AbstractRepository {

	private final PreparedStatement getAllStatement;
	private final PreparedStatement saveStatement;
	private final StudentRepository studentRepository;

	public CourseRepository(Connection connection,
			StudentRepository studentRepository) {
		super(connection);
		this.studentRepository = studentRepository;
		try {
			getAllStatement = connection
					.prepareStatement("SELECT * FROM course");
			saveStatement = connection.prepareStatement(
					"INSERT INTO course(id,name,description,year,location,lect_name,lect_surname) "
							+ "VALUES (?,?,?, ?, ?, ?,?);",
					Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Collection<Course> getAll() {
		try {
			ResultSet rs = getAllStatement.executeQuery();
			Collection<Student> students = studentRepository.getAll();
			Map<Integer, Student> studentMap = new HashMap<Integer, Student>();
			for (Student s : students) {
				studentMap.put(s.getId(), s);
			}
			List<Course> courses = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("id");
				Student stu = studentMap.get(rs.getInt("id"));
				String name = rs.getString("name");
				String description = rs.getString("description");
				int year = rs.getInt("year");
				String location = rs.getString("location");
				String lect_name = rs.getString("lect_name");
				String lect_surname = rs.getString("lect_surname");
				Course course = new Course(id, name, description, year,
						location, lect_name, lect_surname);
				courses.add(course);
			}
			return courses;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Course saveCourse(Course course){
		try {
			saveStatement.setInt(1, course.getId());
			saveStatement.setString(2, course.getName());
			saveStatement.setString(3, course.getDescription());
			saveStatement.setInt(4, course.getYear());
			saveStatement.setString(5, course.getLocation());
			saveStatement.setString(6, course.getLecture_name());
			saveStatement.setString(7, course.getLecture_surname());
			
			saveStatement.executeUpdate();
			
			ResultSet generatedKeys = saveStatement.getGeneratedKeys();
			Course result = null;
			while (generatedKeys.next()) {
				int id = generatedKeys.getInt(1);
				result = new Course(id,course.getName(), 
						course.getDescription(),course.getYear(),course.getLocation(),course.getLecture_name(),course.getLecture_surname());
			}
			return result;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
