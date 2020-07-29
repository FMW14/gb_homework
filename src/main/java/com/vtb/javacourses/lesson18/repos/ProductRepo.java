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
            System.out.println(customerFromDb);

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
            Query query = session.getSession().
                    createQuery("from Product where name=:name");
            query.setParameter("name", name);

            productFromDb = (Product) query.uniqueResult();

            System.out.println(productFromDb);

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
            session.save(product);
//            University university = session.get(University.class, 1L);


//            System.out.println(university);
//            System.out.println("Students: ");
//            for (Student s : university.getStudents()) {
//                System.out.println(s.getName());
//            }

//            Student student = new Student("Zahar", university);
//            session.save(student);
//            university.getStudents().add(student);
            session.getTransaction().commit();

//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            University universityFetch = (University)session.getNamedQuery("withStudents")
//                    .setParameter("id", 2L)
//                    .getSingleResult();
//            session.getTransaction().commit();
//            System.out.println(universityFetch.getStudents());
        } finally {
//            factory.close();
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
//            System.out.println(productFromDb);

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
