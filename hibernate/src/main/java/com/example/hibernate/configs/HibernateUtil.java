package com.example.hibernate.configs;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.model.User;
import com.example.hibernate.model.Wishlist;
import com.example.hibernate.model.Address;
import com.example.hibernate.model.Product;
import com.example.hibernate.model.Role;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();

            // Database Connection Settings
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hibernate_db");
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "mysqls01)");

            // Auto DDL Generation
            configuration.setProperty("hibernate.hbm2ddl.auto", "create");

            // Entity scanning settings
            configuration.setProperty("hibernate.archive.autodetect", "class, hbm");
            configuration.setProperty("hibernate.package_to_scan", "com.example.hibernate.model");

            // Enable SQL logging for debugging
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.format_sql", "true");

            // Caching Settings
//            configuration.setProperty("hibernate.cache.use_second_level_cache", "true");
//            configuration.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");

            // Explicitly specify the mapping for entities
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Wishlist.class);
            configuration.addAnnotatedClass(Address.class);
            configuration.addAnnotatedClass(Product.class);
            configuration.addAnnotatedClass(Role.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
