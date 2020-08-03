package com.vtb.javacourses.lesson19.repos;

import com.vtb.javacourses.lesson19.entities.Lot;
import com.vtb.javacourses.lesson19.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class LotRepo {
    public Lot getById(Long id) {

        if (id == null) {
            throw new NullPointerException();
        }

        Session session = null;
        Lot lotFromDb;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            lotFromDb = session.get(Lot.class, id);
            session.getTransaction().commit();

        } finally {
            if (session != null) {
                session.close();
            }
        }

        return lotFromDb;
    }

    public List<Lot> getAll() {
        Session session = null;
        List<Lot> lotList;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            lotList = HibernateUtil.getSession().createQuery("from Lot").list();
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return lotList;
    }

    public void save(Lot lot) {

        if (lot == null) {
            throw new NullPointerException();
        }

        Session session = null;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.saveOrUpdate(lot);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
