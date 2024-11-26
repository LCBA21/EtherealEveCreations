package com.plusplus.etherealevecreations.service.category;


import com.plusplus.etherealevecreations.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    Category getCategoryById(Long Id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories(String name);
    Category getCategoryByName();
    Category addCategory(Category category);
    Category updateCategory(Category category,Long Id);
    void deleteCategoryById(Long Id);





}
