package com.vtb.javacourses.lesson18.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class CustomerProductId implements Serializable {
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "product_id")
    private Long productId;

    public CustomerProductId(Long customerId, Long productId) {
        this.customerId = customerId;
        this.productId = productId;
    }
}
