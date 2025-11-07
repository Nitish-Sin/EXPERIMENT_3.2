package com.example.springdi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Step 3: Create a configuration class.
 * @Configuration tells Spring this class contains bean definitions.
 */
@Configuration
public class AppConfig {

    /**
     * @Bean tells Spring that this method creates and returns a bean
     * that Spring should manage. The bean's name will be the method name.
     */
    @Bean
    public Course course() {
        // Create and return the Course bean
        return new Course("Jyoti Mam wala JAVA", "5th sem pura");
    }

    /**
     * This @Bean method defines the Student bean.
     * Spring sees that it needs a 'Course' object as a parameter.
     * It automatically finds the 'course()' bean defined above
     * and injects it.
     */
    @Bean
    public Student student(Course course) {
        // Create the Student bean, injecting the Course bean
        return new Student("Nitish", course);
    }
}