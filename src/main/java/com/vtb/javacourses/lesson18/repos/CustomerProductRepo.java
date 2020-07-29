package com.vtb.javacourses.lesson18.repos;

import com.vtb.javacourses.lesson18.HibernateUtil;
import com.vtb.javacourses.lesson18.entities.Customer;
import com.vtb.javacourses.lesson18.entities.CustomerProduct;
import org.hibernate.Session;

import java.util.List;

@Deprecated
public class CustomerProductRepo {
    public CustomerProduct getById(Long id) {

        Session session = null;
        CustomerProduct customerProductFromDb;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            customerProductFromDb = session.get(CustomerProduct.class, id);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return customerProductFromDb;
    }

    public List<Customer> getByCustomerId(Long id) {
        Session session = null;
        List<Customer> customerList;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            customerList = HibernateUtil.getSession().createQuery("from Customer where customer_id=:id").setParameter("id", id).list();
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return customerList;
    }
}
