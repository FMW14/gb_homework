package com.vtb.javacourses.lesson18.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    //    @ManyToMany
//    @JoinTable(
//            name = "customers_products",
//            joinColumns = @JoinColumn(name = "products_id"),
//            inverseJoinColumns = @JoinColumn(name = "customers_id")
//    )
    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CustomerProduct> customers = new ArrayList<>();

    public Product(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
