package com.vtb.javacourses.lesson18.repos;

import com.vtb.javacourses.lesson18.HibernateUtil;
import com.vtb.javacourses.lesson18.entities.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CustomerRepo {
    public Customer getById(Long id){

        Session session = null;
        Customer customerFromDb;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            customerFromDb = session.get(Customer.class, id);
            System.out.println(customerFromDb);

            session.getTransaction().commit();

        } finally {
            if (session != null) {
                session.close();
            }
        }

        return customerFromDb;
    }

    public Customer getByName(String name){
        Session session = null;
        Customer customerFromDb;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            Query query= session.getSession().
                    createQuery("from Customer where name=:name");
            query.setParameter("name", name);

            customerFromDb = (Customer) query.uniqueResult();

            System.out.println(customerFromDb);

            session.getTransaction().commit();

        } finally {
            if (session != null) {
                session.close();
            }
        }

        return customerFromDb;
    }

    public void save(Customer customer){
        Session session = null;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.save(customer);
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

    public Customer deleteById(Long id){

        return null;
    }

    public Customer deleteByName(String name){

        return null;
    }
}
