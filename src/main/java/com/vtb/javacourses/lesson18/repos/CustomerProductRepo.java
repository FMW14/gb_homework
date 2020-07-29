package com.vtb.javacourses.lesson18.repos;

import com.vtb.javacourses.lesson18.HibernateUtil;
import com.vtb.javacourses.lesson18.entities.Customer;
import org.hibernate.Session;

import java.util.List;

public class CustomerProductRepo {
    public List<CustomerRepo> getByCustomerId(Long id){
        Session session = null;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            Customer customerFromDb = session.get(Customer.class, id);
//            SimpleItem simpleItemFromDb = session.get(SimpleItem.class, 1L);
            // SimpleItem simpleItemFromDb2 = session.get(SimpleItem.class, 1L); // Повторное вычитывание объекта (будет вытащен из кеша)
            System.out.println(customerFromDb);
//            session.close();

            session.getTransaction().commit();

        } finally {
            if (session != null) {
                session.close();
            }
        }

        return null;
    }
}
