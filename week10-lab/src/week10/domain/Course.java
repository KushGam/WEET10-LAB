package week10.domain;

/**
 * This class is used to represent details about a subject that can be studied at the university
 * @author Shane Moore
 */
public class Course {
	private String courseCode;
	private String courseName;
	private int numStudents;

	/** Creates a new Course. */
	public Course(String courseCode, String name, int numStudentsEnrolled)
	{
		this.courseCode = courseCode;
		this.courseName = name;
		this.numStudents = numStudentsEnrolled;
	}
	
	public void setCourseCode(String revisedCode) {
		courseCode = revisedCode;
	}
	
	public String getCourseCode() {
		return courseCode;
	}
	
	public void setCourseName(String revisedName) {
		courseName = revisedName;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setNumStudents(int revisedNumber) {
		numStudents = revisedNumber;
	}
	
	public int getNumStudents() {
		return numStudents;
	}


}
