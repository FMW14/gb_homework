package com.vtb.javacourses.lesson21.util;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@AllArgsConstructor
@Configuration
public class PrepareData {

    @Autowired
    private HibernateUtil hibernateUtil;

    @PostConstruct
    public void init(){
        forcePrepareData();
    }

    public void forcePrepareData() {
        Session session = hibernateUtil.getSession();
        try {
            String sql = Files.lines(Paths.get("src/main/resources/lesson21/init_db.sql")).collect(Collectors.joining(" "));
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}