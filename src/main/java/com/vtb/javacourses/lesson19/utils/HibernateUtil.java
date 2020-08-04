package com.vtb.javacourses.lesson19.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    static {
        try {
            loadSessionFactory();
        } catch (Exception e) {
            System.err.println("Exception while initializing hibernate util.. ");
            e.printStackTrace();
        }
    }

    public static void loadSessionFactory() {
        sessionFactory = new Configuration()
                .configure("lesson19/configs/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static Session getSession() throws HibernateException {

        Session retSession = sessionFactory.openSession();

        if (retSession == null) {
            System.err.println("Session is discovered null");
        }

        return retSession;
    }
}