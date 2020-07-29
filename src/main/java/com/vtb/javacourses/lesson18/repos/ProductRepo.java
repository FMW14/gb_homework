package com.vtb.javacourses.lesson18.repos;

import com.vtb.javacourses.lesson18.HibernateUtil;
import com.vtb.javacourses.lesson18.entities.Product;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ProductRepo {
    public Product getById(Long id) {

        Session session = null;
        Product customerFromDb;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            customerFromDb = session.get(Product.class, id);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return customerFromDb;
    }

    public Product getByName(String name) {
        Session session = null;
        Product productFromDb;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            Query query = session.getSession().createQuery("from Product where name=:name");
            query.setParameter("name", name);

            productFromDb = (Product) query.uniqueResult();
            session.getTransaction().commit();

        } finally {
            if (session != null) {
                session.close();
            }
        }

        return productFromDb;
    }

    public void save(Product product) {
        Session session = null;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteById(Long id) {
        Session session = null;
        Product productFromDb;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            productFromDb = session.get(Product.class, id);
            session.delete(productFromDb);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteByName(String name) {
        Session session = null;
        Product productFromDb;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            Query query = session.getSession().
                    createQuery("from Product where name=:name");
            query.setParameter("name", name);
            productFromDb = (Product) query.uniqueResult();
            session.delete(productFromDb);

//            System.out.println(productFromDb);

            session.getTransaction().commit();

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
