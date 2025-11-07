package com.example.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.example.hibernate.util.HibernateUtil;
import java.util.List;

/**
 * Procedure 5: Write a main class to test insert, fetch, update, and delete actions.
 */
public class MainApp {

    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Integer studentId = null;
        Transaction transaction = null;

        // --- 1. CREATE (Insert) a new student ---
        // Procedure 4: Use session.save()
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Student student = new Student("Nitish", 20);
            studentId = (Integer) session.save(student);
            transaction.commit();
            System.out.println("Insert Success! New Student ID: " + studentId);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        // --- 2. READ (Fetch) the student ---
        // Procedure 4: Use session.get()
        try (Session session = sessionFactory.openSession()) {
            Student retrievedStudent = session.get(Student.class, studentId);
            if (retrievedStudent != null) {
                System.out.println("\nRead Success!");
                System.out.println(retrievedStudent);
            }

            // Also read all students
            List<Student> students = session.createQuery("from Student", Student.class).list();
            System.out.println("\nStudent List:");
            students.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // --- 3. UPDATE the student ---
        // Procedure 4: Use session.update()
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Student studentToUpdate = session.get(Student.class, studentId);
            
            if (studentToUpdate != null) {
                studentToUpdate.setName("John Doe");
                studentToUpdate.setAge(23);
                session.update(studentToUpdate);
                transaction.commit();
                System.out.println("\nUpdate Success!");
                System.out.println(studentToUpdate);
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        // --- 4. DELETE the student ---
        // Procedure 4: Use session.delete()
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Student studentToDelete = session.get(Student.class, studentId);
            
            if (studentToDelete != null) {
                session.delete(studentToDelete);
                transaction.commit();
                System.out.println("\nDelete Success!");
                System.out.println(studentToDelete);
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        // 5. Shutdown the SessionFactory
        // Procedure 6: Resource closing
        HibernateUtil.shutdown();
    }
}