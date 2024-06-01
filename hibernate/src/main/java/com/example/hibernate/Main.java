package com.example.hibernate;

import com.example.hibernate.configs.HibernateUtil;
import com.example.hibernate.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
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

        Address address = new Address();
        address.setAddressLine1("123 Main Street");
        address.setCity("New York");
        address.setState("NY");
        address.setZipCode("10001");

        User user = new User();
        user.setName("John Doe");
        user.setGender("Male");
        user.setDescription("Lorem ipsum..");
        user.setUniqueCode("ABC123");
        user.setStatus(User.Status.ACTIVE);
        user.setProfileImg(null);
        user.setNonPersistentField(123);
        System.out.println(user);

        Calendar calendar = Calendar.getInstance();
        calendar.set(1997, Calendar.FEBRUARY, 10);
        Date birthDate = calendar.getTime();
        user.setBirthDate(birthDate);

        user.setNextYearBirthday(null);
        user.setSomeUntrackedField("Some value");
        user.setAddress(address);

        Role role = new Role();
        role.setName("Admin");

        Product product = new Product();
        product.setName("Smartphone");
        product.setPrice(499.99);

        Cart cart = new Cart();
        cart.setUser(user);

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(2);
        cartItem.setProduct(product);
        cartItem.setCart(cart);

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);

        UserOrders order = new UserOrders();
        order.setUser(user);

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(1);
        orderItem.setProduct(product);
        orderItem.setOrders(order);

        // Set relationships
        user.getRoles().add(role);
        user.getProducts().add(product);
        user.setCart(cart);
        user.getOrders().add(order);
        user.setWishlist(wishlist);

        role.getUsers().add(user);
        wishlist.getProducts().add(product);

        // Build the SessionFactory using the provided java configuration
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        // Begin a transaction
        Transaction transaction = session.beginTransaction();

        try (sessionFactory; session) {
            // Save entities in the correct order
//            session.persist(address);
            session.persist(user);
            session.persist(role);
            session.persist(product);
            session.persist(cart);
            session.persist(cartItem);
            session.persist(wishlist);
            session.persist(order);
            session.persist(orderItem);

            // Commit the transaction
            transaction.commit();
            logger.info("Entities saved successfully!");
        }
        // Close the session
        // Ensure to close the SessionFactory when the application exits
        // Todo: Fix caching issue: ClassNotFoundException: org.glassfish.jaxb.runtime.v2.ContextFactory
        // Todo: HQL & HCQL, JPA, Pagination
    }
}
