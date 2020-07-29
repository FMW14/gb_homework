package com.vtb.javacourses.lesson18.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
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
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<CustomerProduct> customerProducts = new ArrayList<>();

//    private List<Product> products = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public Customer() {
    }

    public void addProduct(Product newProduct){
        this.customerProducts.add( new CustomerProduct(this, newProduct));
    }
}
