package com.plusplus.etherealevecreations.service.product.Impl;


import com.plusplus.etherealevecreations.entity.Category;
import com.plusplus.etherealevecreations.entity.Product;
import com.plusplus.etherealevecreations.exceptions.ProductNotFoundException;
import com.plusplus.etherealevecreations.repository.CategoryRepository;
import com.plusplus.etherealevecreations.repository.ProductRepository;
import com.plusplus.etherealevecreations.request.AddProductRequest;
import com.plusplus.etherealevecreations.request.ProductUpdateRequest;
import com.plusplus.etherealevecreations.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl  implements ProductService {



    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;



    @Override
    public Product addProduct(AddProductRequest request) {

            //check if the category is found i the DB

        Category category= Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() ->{
                    Category newCategory =new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);
        return productRepository.save(createProduct(request,category));
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Long id) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    // Update the existing product with data from the request
                    Product updatedProduct = updateExistingProduct(existingProduct, request);
                    // Save the updated product and return it
                    return productRepository.save(updatedProduct);
                })
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
    }

    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request){
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setQuantity(request.getQuantity());
        existingProduct.setDescription(request.getDescription());

        Category category=categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;

    }

    private Product createProduct(AddProductRequest request, Category category){
        return  new Product(

                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getQuantity(),
                request.getDescription(),
                category
        );

    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product not found"));

    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete,()->{throw new ProductNotFoundException("Ptoduct not found");});

    }

    @Override
    public void updateProductById(Product product, Long productId) {

    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category,brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand,name);
    }


//    count inventory of hair
    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand,name);
    }
}
