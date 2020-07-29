package com.vtb.javacourses.lesson18;

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
                .configure("lesson18/configs/hibernate.cfg.xml")
                .buildSessionFactory();

//        Configuration configuration = new Configuration();
//        configuration.configure("/j2n-hibernate.cfg.xml");
//        configuration.addAnnotatedClass(Employee.class);
//        ServiceRegistry srvcReg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//        sessionFactory = configuration.buildSessionFactory(srvcReg);
    }

    public static Session getSession() throws HibernateException {

        Session retSession = null;
        try {
            retSession = sessionFactory.openSession();
        } catch (Throwable t) {
            System.err.println("Exception while getting session.. ");
            t.printStackTrace();
        }
        if (retSession == null) {
            System.err.println("session is discovered null");
        }

        return retSession;
    }
}
