package com.vtb.javacourses.lesson18;

import com.vtb.javacourses.lesson18.entities.Customer;
import com.vtb.javacourses.lesson18.repos.CustomerRepo;

public class Main {
    public static void main(String[] args) {
        PrepareData.forcePrepareData();
        CustomerRepo customerRepo = new CustomerRepo();

        Customer customer1 = new Customer("Bob");
        customerRepo.save(customer1);
        customerRepo.getById(2L);
        customerRepo.getByName("Bob");
//        customerRepo.getById(3L);
//        customerRepo.getById(4L);

    }
}
