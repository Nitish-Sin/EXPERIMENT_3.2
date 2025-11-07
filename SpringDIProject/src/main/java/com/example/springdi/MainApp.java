package com.example.springdi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Step 4: Load the Spring context in the main method.
 */
public class MainApp {

    public static void main(String[] args) {
        
        // 1. Initialize the Spring context using our Java-based configuration
        // We use AnnotationConfigApplicationContext for @Configuration classes
        ApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

        // 2. Fetch the Student bean from the context
        // Spring has already created the Student and its required Course
        Student student = context.getBean(Student.class);

        // 3. Call the method to display the details
        // This will print the sample output
        student.displayStudentInfo();

        // 4. Close the context
        ((AnnotationConfigApplicationContext) context).close();
    }
}