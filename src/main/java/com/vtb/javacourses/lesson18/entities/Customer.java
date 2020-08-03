package com.vtb.javacourses.lesson18.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
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

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<CustomerProduct> customerProducts = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addProduct(Product newProduct) {
        this.customerProducts.add(new CustomerProduct(this, newProduct));
    }
}
