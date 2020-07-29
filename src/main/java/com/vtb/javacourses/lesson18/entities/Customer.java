package com.vtb.javacourses.lesson18.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    //    @ManyToMany
//    @JoinTable(
//            name = "customers_products",
//            joinColumns = @JoinColumn(name = "customers_id"),
//            inverseJoinColumns = @JoinColumn(name = "products_id")
//    )
    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Product> products = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }
}
