package com.plusplus.etherealevecreations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private String currency;
    private BigDecimal price; // Should be in major units (e.g., 10.50 USD)
    private int quantity;
}
