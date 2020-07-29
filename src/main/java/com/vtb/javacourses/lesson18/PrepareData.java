package com.vtb.javacourses.lesson18;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class PrepareData {
    public static void forcePrepareData() {
//        SessionFactory factory = new Configuration()
//                .configure("configs/hibernate.cfg.xml")
//                .buildSessionFactory();
//        HibernateUtil.loadSessionFactory();
        Session session = HibernateUtil.getSession();
        try {
//            Path test = new Pa
            System.out.println("file init = " + Files.exists(Paths.get("src/main/resources/lesson18/init_db.sql")));
            System.out.println("start parse init_db");
            String sql = Files.lines(Paths.get("src/main/resources/lesson18/init_db.sql")).collect(Collectors.joining(" "));
            System.out.println("end parse init_db");
//            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}
