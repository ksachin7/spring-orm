package com.example.hibernate;

import com.example.hibernate.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(Main.class.getName());

        // Create a Hibernate Configuration object and configure it
        Configuration config= new Configuration();
        config.configure("hibernate.config.xml");

        // Build the SessionFactory using the provided configuration
        logger.info("Building SessionFactory...");
        SessionFactory sessionFactory = config.buildSessionFactory();
        if (sessionFactory != null) {
            logger.info("SessionFactory created successfully.");
        } else {
            logger.severe("Failed to create SessionFactory.");
        }
        // Use the SessionFactory to perform database operations (e.g., CRUD operations)
// Open a session
//        try (Session session = sessionFactory.openSession()) {
//            // Begin a transaction
//            Transaction transaction = session.beginTransaction();
//
//            // Create a new User object
//            User user = new User();
//
//            // Set user properties using setter methods
//            user.setUniqueCode("u12345");
//            user.setName("John Doe");
//            user.setGender("Male");
//            user.setAge(21);
//            user.setDescription("Sample description");
//            user.setStatus(User.Status.ACTIVE);
//            user.setBirthDate(new Date());
//
//            // Save the user to the database
//            session.save(user);
//
//            // Commit the transaction
//            transaction.commit();
//            System.out.println("User saved successfully with ID: " + user.getId());
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // Close the SessionFactory when done
//            sessionFactory.close();
//        }

        // Ensure to close the SessionFactory when the application exits
        sessionFactory.close();
    }
}
