package com.vtb.javacourses.lesson18;

import com.vtb.javacourses.lesson18.entities.Customer;
import com.vtb.javacourses.lesson18.entities.CustomerProduct;
import com.vtb.javacourses.lesson18.entities.Product;
import com.vtb.javacourses.lesson18.repos.CustomerRepo;
import com.vtb.javacourses.lesson18.repos.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PrepareData.forcePrepareData();
        CustomerRepo customerRepo = new CustomerRepo();
        ProductRepo productRepo = new ProductRepo();

        List<CustomerProduct> customerProducts1 = new ArrayList<>();

        Customer customer1 = new Customer("Bob");
        customerRepo.save(customer1);

        Product product1 = new Product("prod1", 100);
        customerProducts1.add(new CustomerProduct(customer1, product1));
        customer1.setCustomerProducts(customerProducts1);
        customerRepo.save(customer1);

        customerRepo.deleteByName("Bob");
//        customerRepo.getById(4L);
        
        Scanner scanner = new Scanner(System.in);
        ScannerParser scannerParser = new ScannerParser();
        boolean waiting = true;
        while (waiting) {
            if (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                Map<String, String> parsedLine = scannerParser.parseLine(s);

                if (parsedLine.get("command").equals("/showProductsByConsumer")) {
                    printProductsByCustomerName(parsedLine.get("arg1"));
                }

                if (parsedLine.get("command").equals("/showConsumersByProductTitle")) {
                    printPCustomersByProductName(parsedLine.get("arg1"));
                }

                if (parsedLine.get("command").equals("/deleteCustomer")) {
                    deleteCustomerByName(parsedLine.get("arg1"));
                }

                if (parsedLine.get("command").equals("/deleteProduct")){
                    deleteProductByName(parsedLine.get("arg1"));
                }

                if (parsedLine.get("command").equals("/buy")){
//                    deleteProductByName(parsedLine.get("arg1"));
                    // TODO: 30.07.2020 parselong 
                    buy(Long.parseLong(parsedLine.get("arg1")), Long.parseLong(parsedLine.get("arg2")));
                }

                if (parsedLine.get("command").equals("/end")){
                    waiting = false;
                }
            }
        }
    }

    public static void printProductsByCustomerName(String name) {
        CustomerRepo customerRepo = new CustomerRepo();
        Customer customer = customerRepo.getByName(name);

        if (customer.getCustomerProducts() != null || !customer.getCustomerProducts().isEmpty()) {
            for (CustomerProduct cp : customer.getCustomerProducts()) {
                System.out.println(cp.getProduct().getName());
            }
        } else {
            System.out.println("Customer has no products");
        }

    }

    public static void printPCustomersByProductName(String name) {
        ProductRepo productRepo = new ProductRepo();
        Product product = productRepo.getByName(name);

        try{
            if(product.getCustomerProducts().isEmpty()){
                throw new NullPointerException("No customers"); //TODO exc
            } else {
                for (CustomerProduct cp : product.getCustomerProducts()) {
                    System.out.println(cp.getCustomer().getName());
                }
            }

        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public static void deleteProductByName(String name){
        ProductRepo productRepo = new ProductRepo();
        productRepo.deleteByName(name);
    }

    public static void deleteCustomerByName(String name){
        CustomerRepo customerRepo = new CustomerRepo();
        customerRepo.deleteByName(name);
    }

    public static void buy(Long customerId, Long productId){
        ProductRepo productRepo = new ProductRepo();
        CustomerRepo customerRepo = new CustomerRepo();

        Customer customer = customerRepo.getById(customerId);
        customer.addProduct(productRepo.getById(productId));
//        customer.getCustomerProducts().add(new CustomerProduct(customer, productRepo.getById(productId)));
        customerRepo.save(customer);
    }
}
