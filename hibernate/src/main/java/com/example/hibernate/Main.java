package com.example.hibernate;

import com.example.hibernate.configs.HibernateUtil;
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

        /* for xml configurations
        // Create a Hibernate Configuration object and configure it
        Configuration config= new Configuration();
        config.configure("hibernate.config.xml");

        // Build the SessionFactory using the provided xml configuration
        SessionFactory sessionFactory = config.buildSessionFactory();
        */

        // Build the SessionFactory using the provided java configuration
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Use the SessionFactory to perform database operations (e.g., CRUD operations)


        // Todo: Hibernate java config, eager and lazy, Lifecycle methods and annotations

        // Todo: Fix caching issue: ClassNotFoundException: org.glassfish.jaxb.runtime.v2.ContextFactory
        // Todo: HQL & HCQL, JPA, Pagination

        // Ensure to close the SessionFactory when the application exits
        sessionFactory.close();
    }
}
