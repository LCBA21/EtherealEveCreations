package com.plusplus.etherealevecreations.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private int quantity;

    private BigDecimal unitprice = BigDecimal.ZERO;

    private BigDecimal totalprice = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @EqualsAndHashCode.Exclude // Avoid cyclic dependency
    @JsonBackReference //prevent nested json object
    private Cart cart;

    public void setTotalPrice() {
        this.totalprice = this.unitprice.multiply(BigDecimal.valueOf(this.quantity));
    }
}
