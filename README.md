This is for academic purpose

Java Persistence and Spring Framework Projects

Welcome! This repository contains a collection of projects demonstrating key concepts in Java persistence and the Spring framework. The projects are organized by difficulty to show a clear learning progression, from basic database operations to complex, transaction-managed applications.

🚀 Projects Overview

hibernate-oracle-crud (Easy): A simple Java console application showing direct Hibernate ORM usage for basic CRUD operations with an Oracle database.

spring-di-project (Medium): A lightweight project focused on explaining and demonstrating Spring's core Inversion of Control (IoC) and Dependency Injection (DI) features.

spring-bank-app (Hard): A complete, multi-layered Spring application that integrates Spring ORM, connection pooling, and declarative transaction management to simulate atomic bank transfers.

🛠️ Common Prerequisites

Before running any of these projects, you will need the following software installed:

Java JDK: Version 11 or higher

Apache Maven: To build projects and manage dependencies

Oracle Database: An accessible instance (like Oracle XE)

An IDE: IntelliJ IDEA or Eclipse is recommended

🚀 Level 1: Easy - hibernate-oracle-crud

This project is the starting point. It demonstrates how to use Hibernate directly to connect a Java application to an Oracle database and perform basic Create, Read, Update, and Delete (CRUD) operations.

Key Concepts Demonstrated

Hibernate (ORM): Using Hibernate as an Object-Relational Mapping (ORM) tool.

Configuration: Setting up hibernate.cfg.xml to connect to an Oracle database.

SessionFactory and Session: Understanding the core Hibernate objects for managing database connections and operations.

Entity Mapping: Using JPA annotations (@Entity, @Id, @Column) to map a Java POGO (Plain Old Java Object) to a database table.

CRUD Operations:

Create: session.save(object)

Read: session.get(class, id)

Update: session.update(object)

Delete: session.delete(object)

Hibernate Query Language (HQL): Writing basic FROM Employee queries.

How to Run

Database Setup: Manually create the required table (e.g., EMPLOYEE) in your Oracle database.

Configure: Edit src/main/resources/hibernate.cfg.xml and update the database URL, username, and password.

Run: Execute the Main.java class to see the CRUD operations in action.

🚀 Level 2: Medium - spring-di-project

This project introduces the Spring Framework. The focus is not on databases, but on Spring's core container and how it manages your application's components. It demonstrates the "magic" of Inversion of Control (IoC) and Dependency Injection (DI).

Key Concepts Demonstrated

Spring Core Container: Understanding the ApplicationContext.

Inversion of Control (IoC): How Spring creates and manages the lifecycle of your objects (beans) instead of you.

Dependency Injection (DI):

Constructor Injection: Passing dependencies via the constructor.

Setter Injection: Passing dependencies via setter methods.

Field Injection: Using @Autowired directly on fields.

Configuration Styles:

Java-based (@Configuration): Using @Configuration and @Bean to define beans.

Annotation-based (@Component): Using @Component, @Service, @Repository to auto-detect beans.

XML-based (Legacy): (Optional) Defining beans in an applicationContext.xml file.

Decoupling: Writing code that depends on interfaces, not concrete implementations, with Spring "injecting" the correct implementation at runtime.

How to Run

Build: Run mvn clean install.

Run: Execute the MainApp.java class.

Observe: Check the console output to see how services are injected and their methods are called without a single new MyService() in the main application logic.

🚀 Level 3: Hard - spring-bank-app

This is the capstone project. It combines all concepts from the previous projects and adds Spring's powerful data access and transaction management features. It builds a realistic, multi-layered application that simulates bank transfers, focusing on data integrity and atomic operations.

Key Concepts Demonstrated

Spring Framework: Full use of DI, @Autowired, and Java-based configuration (@Configuration).

Layered Architecture: A clean separation of concerns:

config: Spring configuration.

model: Hibernate/JPA entities (Account, Transaction).

dao: Data Access Object (Repository) layer, responsible only for database operations.

service: Business logic layer, responsible for orchestrating DAO calls.

exception: Custom exceptions for business rules.

Spring ORM: Integrating Spring with Hibernate using LocalSessionFactoryBean (no more hibernate.cfg.xml).

Connection Pooling: Using HikariCP (via HikariDataSource) for high-performance database connections.

Spring Transaction Management:

PlatformTransactionManager: The core Spring bean for handling transactions.

Declarative Transactions (@Transactional): The star of the show. Using a single annotation on a service method to make all database operations within it atomic.

Automatic Rollbacks: Demonstrating how throwing a RuntimeException (like InsufficientFundsException) from a @Transactional method automatically reverses all previous database actions in that method, ensuring data is never corrupted.

hibernate.hbm2ddl.auto=validate: Using Spring/Hibernate to validate that the database schema matches the Java entities on startup.

How to Run

Configure: Edit src/main/resources/database.properties with your Oracle database URL, username, and password.

Database Setup: Open your SQL client (like SQL Developer) and run the entire db_setup.sql script to create the tables, sequence, and initial data.

Build: Run mvn clean install.

Run: Execute the MainApp.java class.

Observe: Watch the console output. You will see:

A successful transfer.

An attempted transfer that fails due to insufficient funds.

A final balance check that proves the failed transaction was fully rolled back, and the account balances are correct.
