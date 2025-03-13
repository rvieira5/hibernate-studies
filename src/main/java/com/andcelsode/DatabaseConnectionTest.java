package com.andcelsode;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {
            System.out.println("\n\n\n###############################");
            System.out.println("Database connection successfully established!");
            System.out.println("###############################\n\n\n");
        } catch (Exception e) {
            System.out.println("\n\n\n###############################");
            System.err.println("Error connecting to database: " + e.getMessage());
            System.out.println("###############################\n\n\n");
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}