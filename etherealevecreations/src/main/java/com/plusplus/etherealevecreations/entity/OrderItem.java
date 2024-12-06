package com.plusplus.etherealevecreations.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private int quantity;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    public OrderItem(int quantity, Product product, Order order, BigDecimal price) {
        this.quantity = quantity;
        this.product = product;
        this.order = order;
        this.price = price;
    }
}
