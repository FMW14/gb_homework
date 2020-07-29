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

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("customerId")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private Product product;

    @Column(name = "price")
    private Integer price;
}
