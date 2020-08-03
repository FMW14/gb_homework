package com.vtb.javacourses.lesson21.repos;

import com.vtb.javacourses.lesson21.entities.Product;
import com.vtb.javacourses.lesson21.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("singleton")
public class ProductRepo {
    public Product getById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }

        Session session = null;
        Product productFromDb;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            productFromDb = session.get(Product.class, id);
            session.getTransaction().commit();

        } finally {
            if (session != null) {
                session.close();
            }
        }

        return productFromDb;
    }

    public List<Product> getAll() {
        Session session = null;
        List<Product> productList;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            productList = HibernateUtil.getSession().createQuery("from Product").list();
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return productList;
    }

    public void save(Product product) {

        if (product == null) {
            throw new IllegalArgumentException("product cannot be null");
        }

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
}
