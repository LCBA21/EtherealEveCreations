package com.plusplus.etherealevecreations.request;

import com.plusplus.etherealevecreations.entity.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data

public class ProductUpdateRequest {

    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int quantity;
    private String description;
    private Category category;
}
