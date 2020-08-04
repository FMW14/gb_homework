package com.vtb.javacourses.lesson21.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HibernateUtil {
    private SessionFactory sessionFactory = null;

    @PostConstruct
    public void init(){
        loadSessionFactory();
    }

    public void loadSessionFactory() {
        sessionFactory = new Configuration()
                .configure("lesson21/configs/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public Session getSession() throws HibernateException {

        Session retSession = sessionFactory.openSession();
        if (retSession == null) {
            System.err.println("Session is discovered null");
        }

        return retSession;
    }
}
