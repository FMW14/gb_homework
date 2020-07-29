package com.vtb.javacourses.lesson18.repos;

import com.vtb.javacourses.lesson18.HibernateUtil;
import com.vtb.javacourses.lesson18.entities.Customer;
import com.vtb.javacourses.lesson18.entities.CustomerProduct;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CustomerProductRepo {
    public CustomerProduct getById(Long id) {

        Session session = null;
        CustomerProduct customerProductFromDb;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            customerProductFromDb = session.get(CustomerProduct.class, id);
            System.out.println(customerProductFromDb);

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
        List<Customer> customerList = new ArrayList<>();

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

//            MultiIdentifierLoadAccess<Customer> multiLoadAccess = session.byMultipleIds(Customer.class);
//            List<Customer> customers = multiLoadAccess.multiLoad(1L, 2L, 3L);

            customerList = HibernateUtil.getSession().createQuery("from Customer where customer_id=:id").setParameter("id", id).list();


//            Customer customerFromDb = session.get(Customer.class, id);
            System.out.println(customerList);
            session.getTransaction().commit();

        } finally {
            if (session != null) {
                session.close();
            }
        }

        return customerList;
    }
}
