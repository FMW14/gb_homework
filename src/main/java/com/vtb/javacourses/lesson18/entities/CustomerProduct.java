package com.vtb.javacourses.lesson18.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "customer_product")
public class CustomerProduct {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private Product product;

    @Column(name = "price")
    private Integer price;

//    public CustomerProduct(Customer customer, Product product, Integer price) {
//        this.customer = customer;
//        this.product = product;
//        this.price = price;
//    }

    public CustomerProduct(Customer customer, Product product) {
        this.customer = customer;
        this.product = product;
        this.price = product.getPrice();
    }

    public CustomerProduct() {
    }

    @Override
    public String toString() {
        return "CustomerProduct{" +
                "id=" + id +
                ", customerId=" + customer.getId() +
                ", productId=" + product.getId() +
                ", price=" + price +
                '}';
    }
}
