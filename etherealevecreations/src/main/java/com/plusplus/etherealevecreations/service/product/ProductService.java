package com.plusplus.etherealevecreations.service.product;

import com.plusplus.etherealevecreations.entity.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void deleteProductById(Long id);
    void updateProductById(Product product,Long productId);
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category,String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand,String name);
    Long countProductsByBrandAndName(String brand,String name);






}
