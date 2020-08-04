package com.vtb.javacourses.lesson18.repos;

import com.vtb.javacourses.lesson18.utils.HibernateUtil;
import com.vtb.javacourses.lesson18.entities.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CustomerRepo {
    public Customer getById(Long id) {

        if (id == null) {
            throw new NullPointerException();
        }

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

        if (name == null) {
            throw new NullPointerException();
        }

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
        if (customer == null) {
            throw new NullPointerException();
        }

        Session session = null;

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
        if (id == null) {
            throw new NullPointerException();
        }

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

        if (name == null) {
            throw new NullPointerException();
        }

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
            if (customerFromDb != null) {
                session.delete(customerFromDb);
            } else {
                throw new NullPointerException("Customer not found");
            }

            session.getTransaction().commit();

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
