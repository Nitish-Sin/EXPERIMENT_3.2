package com.example.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * This file tells Hibernate WHAT your database table looks like.
 * We are telling it to name the table "studentz".
 */
@Entity
@Table(name = "studentz") // <-- The table name is changed here
public class Student {

    /**
     * @Id marks this as the primary key.
     * We also changed the sequence name to match the new table name.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentz_seq_gen")
    @SequenceGenerator(name = "studentz_seq_gen", sequenceName = "STUDENTZ_SEQ", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    // A no-argument constructor is required by Hibernate
    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // --- Getters and Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age;
    }
}