package com.example.springdi;

/**
 * Step 2: Create a 'Student' class.
 * This class depends on the Course class.
 */
public class Student {

    private String name;
    private Course course;

    /**
     * The dependency (Course) is injected via the constructor.
     */
    public Student(String name, Course course) {
        this.name = name;
        this.course = course;
    }

    /**
     * Step 5: Call getter methods (or a display method) to show details.
     */
    public void displayStudentInfo() {
        System.out.println(
            "Student: " + name + 
            ", Course: " + course.getCourseName() + 
            ", Duration: " + course.getDuration()
        );
    }
}