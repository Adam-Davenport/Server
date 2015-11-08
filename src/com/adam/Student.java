package com.adam;

import java.util.ArrayList;

public class Student {
	//Attributes
	private String firstName;
	private String lastName;
	private String major;
	private int gradYear;

	//Constructor
	public Student(String f, String l, String m, int y)
	{
		firstName = f;
		lastName = l;
		major = m;
		gradYear = y;
	}
	//Creating a list of students
	public static ArrayList<Student> studentList()
	{
		Student vader = new Student("Darth", "Vader", "Engineering", 1977);
		Student ben = new Student("Ben", "Kenobi", "Engineering", 1977);
		ArrayList<Student> students = new ArrayList<>();
		students.add(vader);
		students.add(ben);
		return students;
	}
	//Get methods
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMajor() {
		return major;
	}

	public int getGradYear() {
		return gradYear;
	}

	//Setter methods

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void setGradYear(int gradYear) {
		this.gradYear = gradYear;
	}
}
