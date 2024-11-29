package com.plusplus.etherealevecreations.controller;


import com.plusplus.etherealevecreations.entity.Category;
import com.plusplus.etherealevecreations.exceptions.AlreadyExistsException;
import com.plusplus.etherealevecreations.exceptions.ResourceNotFoundException;
import com.plusplus.etherealevecreations.response.ApiResponse;
import com.plusplus.etherealevecreations.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories() {

        try {

            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("Found!", categories));

        } catch (Exception e) {

            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error:", INTERNAL_SERVER_ERROR));
        }
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategories(@RequestBody Category name) {
        try {
            Category category = categoryService.addCategory(name);
            return ResponseEntity.ok(new ApiResponse("Success", category));


        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @GetMapping("/category/{Id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long Id) {

        try {
            Category category = categoryService.getCategoryById(Id);

            return ResponseEntity.ok((new ApiResponse("Found", category)));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }


    }


    @GetMapping("/category/{name}")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name) {

        try {
            Category category = categoryService.getCategoryByName(name);

            return ResponseEntity.ok((new ApiResponse("Found", category)));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @DeleteMapping("/deleteCategory/{Id}")
    public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable Long  Id) {

        try {
           categoryService.deleteCategoryById(Id);

            return ResponseEntity.ok((new ApiResponse("Found", null)));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/updateCategory/{Id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long  Id,@RequestBody Category category) {

        try {
         Category updatedCategory=categoryService.updateCategory(category,Id);

            return ResponseEntity.ok((new ApiResponse("Update Success!", updatedCategory)));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }



}
