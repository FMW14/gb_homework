package com.vtb.javacourses.lesson21.repos;

import com.vtb.javacourses.lesson21.entities.User;
import com.vtb.javacourses.lesson21.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepo {
    public User getById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }

        Session session = null;
        User userFromDb;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            userFromDb = session.get(User.class, id);
            session.getTransaction().commit();

        } finally {
            if (session != null) {
                session.close();
            }
        }

        return userFromDb;
    }

    public List<User> getAll() {
        Session session = null;
        List<User> userList;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            userList = HibernateUtil.getSession().createQuery("from User").list();
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return userList;
    }

    public void save(User user) {

        if (user == null) {
            throw new IllegalArgumentException("user cannot be null");
        }

        Session session = null;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
