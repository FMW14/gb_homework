package com.vtb.javacourses.lesson18;

import com.vtb.javacourses.lesson18.entities.Customer;
import com.vtb.javacourses.lesson18.entities.CustomerProduct;
import com.vtb.javacourses.lesson18.entities.Product;
import com.vtb.javacourses.lesson18.repos.CustomerRepo;
import com.vtb.javacourses.lesson18.repos.ProductRepo;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PrepareData.forcePrepareData();
        CustomerRepo customerRepo = new CustomerRepo();
        ProductRepo productRepo = new ProductRepo();

        List<CustomerProduct> customerProducts1 = new ArrayList<>();

        Customer customer1 = new Customer("Bob");

        customerRepo.save(customer1);
//        customerRepo.getById(2L);
//        customerRepo.getByName("Bob");

        Product product1 = new Product("prod1", 100);
//        productRepo.save(product1);
//        productRepo.getById(1L);
//        productRepo.getByName("prod1");
//        productRepo.deleteById(1L);
//        productRepo.deleteByName("prod1");

        customerProducts1.add(new CustomerProduct(customer1, product1));
        customer1.setCustomerProducts(customerProducts1);
        customerRepo.save(customer1);

        customerRepo.getById(4L);
//        customerRepo.deleteById(4L);
//        customerRepo.deleteByName("Bob");
//        customerRepo.deleteByName("name1");
//        customerRepo.getById(3L);
//        customerRepo.getById(4L);

    }
}
