package week10.ui;

import java.util.ArrayList;

import week10.domain.Course;

public class CourseInfoProgram {
	
	/** Entry point for program to begin */
	public static void main(String[] args)
	{
		ArrayList<Course> theCourses;

		// Create some course objects:
		theCourses = new ArrayList<Course>();
		
		theCourses.add( new Course("ITECH1103","Big Data and Analytics",102) );
		theCourses.add( new Course("ITECH2003","Web Design",48) );
		theCourses.add( new Course("ITECH2004","Data Modelling",73) );
		theCourses.add( new Course("ITECH2306","Agile Programming",85) );
		
		// Student to create an instance of their frame here...
		
		
	}
}