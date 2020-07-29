package com.vtb.javacourses.lesson18.repos;

import com.vtb.javacourses.lesson18.HibernateUtil;
import com.vtb.javacourses.lesson18.entities.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CustomerRepo {
    public Customer getById(Long id) {

        Session session = null;
        Customer customerFromDb;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            customerFromDb = session.get(Customer.class, id);
            session.getTransaction().commit();

        } finally {
            if (session != null) {
                session.close();
            }
        }

        return customerFromDb;
    }

    public Customer getByName(String name) {
        Session session = null;
        Customer customerFromDb;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            Query query = session.getSession().
                    createQuery("from Customer where name=:name");
            query.setParameter("name", name);

            customerFromDb = (Customer) query.uniqueResult();
            session.getTransaction().commit();

        } finally {
            if (session != null) {
                session.close();
            }
        }

        return customerFromDb;
    }

    public void save(Customer customer) {
        Session session = null;

        if (customer == null){
            return;
        }

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            session.saveOrUpdate(customer);

            session.getTransaction().commit();
        } finally {
//            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteById(Long id) {
        Session session = null;
        Customer customerFromDb;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            customerFromDb = session.get(Customer.class, id);
            session.delete(customerFromDb);
//            System.out.println(customerFromDb);

            session.getTransaction().commit();

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteByName(String name) {
        Session session = null;
        Customer customerFromDb;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
//            Query query = session.getSession().createQuery("from Customer where name=:name");
//            query.setParameter("name", name);
//            customerFromDb = (Customer) query.uniqueResult();
//
            customerFromDb = getByName(name);
            session.delete(customerFromDb);
            session.getTransaction().commit();

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
