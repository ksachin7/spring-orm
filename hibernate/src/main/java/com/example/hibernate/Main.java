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

        // Use the SessionFactory to perform database operations (e.g., CRUD operations)


        // Ensure to close the SessionFactory when the application exits
        sessionFactory.close();
    }
}
