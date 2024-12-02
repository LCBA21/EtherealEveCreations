package com.plusplus.etherealevecreations.controller;


import com.plusplus.etherealevecreations.entity.Product;
import com.plusplus.etherealevecreations.exceptions.ResourceNotFoundException;
import com.plusplus.etherealevecreations.request.AddProductRequest;
import com.plusplus.etherealevecreations.request.ProductUpdateRequest;
import com.plusplus.etherealevecreations.response.ApiResponse;
import com.plusplus.etherealevecreations.service.image.ImageService;
import com.plusplus.etherealevecreations.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/getAllProducts")
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(new ApiResponse("Products fetched successfully", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to fetch products", e.getMessage()));
        }
    }


    @GetMapping("/product/{productId}")
public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId){

    try {
        Product product=productService.getProductById(productId);
        return ResponseEntity.ok(new ApiResponse("Success",product));
    } catch (ResourceNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
    }

}



    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product){


        try {
            Product Newproduct=productService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("Added Product",Newproduct));


        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));

        }
    }



    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody AddProductRequest product){


        try {
            Product Newproduct=productService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("Added Product",Newproduct));


        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));

        }
    }



    @PutMapping("/UpdateProduct/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductUpdateRequest request, Long productId){

        try {
            Product updatedProduct=productService.updateProduct(request,productId);
            return ResponseEntity.ok(new ApiResponse("Success!",updatedProduct));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }



    @DeleteMapping("/DeleteProduct/{productId}")
    public  ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId){
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Product Deleted Successfully",productId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));

        }
    }


    @GetMapping("/getProductByBrandNameAndProductName/{brandName}/{productName}")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@PathVariable String brandName,@PathVariable String productName){


        try {
            List<Product> products=productService.getProductsByBrandAndName(brandName,productName);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No Product Found",null));
            }

            return ResponseEntity.ok(new ApiResponse("Product Found",products));


        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));

        }
    }








}





