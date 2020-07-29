package com.vtb.javacourses.lesson18.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer_product")
public class CustomerProduct {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @EmbeddedId
//    CustomerProductId customerProductId;

    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("product_id")
    private Product product;

    @Column(name = "price")
    private Integer price;

    public CustomerProduct(Customer customer, Product product, Integer price) {
        this.customer = customer;
        this.product = product;
        this.price = price;
    }

    public CustomerProduct() {
    }
}
