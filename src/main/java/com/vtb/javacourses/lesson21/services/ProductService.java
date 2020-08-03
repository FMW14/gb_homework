package com.vtb.javacourses.lesson21.services;

import com.vtb.javacourses.lesson21.entities.Product;
import com.vtb.javacourses.lesson21.repos.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductService {
    private ProductRepo productRepo;

    public void save(Product product) {
        productRepo.save(product);
    }

    public Product getById(Long id) {
        return productRepo.getById(id);
    }

    public List<Product> getAll() {
        return productRepo.getAll();
    }
}
