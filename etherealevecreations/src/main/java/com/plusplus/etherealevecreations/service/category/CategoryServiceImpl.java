package com.plusplus.etherealevecreations.service.category;

import com.plusplus.etherealevecreations.entity.Category;
import com.plusplus.etherealevecreations.exceptions.AlreadyExistsException;
import com.plusplus.etherealevecreations.exceptions.ProductNotFoundException;
import com.plusplus.etherealevecreations.exceptions.ResourceNotFoundException;
import com.plusplus.etherealevecreations.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long Id) {
        return categoryRepository.findById(Id)
                .orElseThrow(()-> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return null;
    }


    @Override
    public Category updateCategory(Category category,Long Id) {
       return Optional.ofNullable(getCategoryById(Id)).map(oldCategory ->{

            oldCategory.setName(category.getName());
            return categoryRepository.save(oldCategory);
        }).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public void deleteCategoryById(Long Id) {
        categoryRepository.findById(Id).ifPresentOrElse(categoryRepository::delete,()->{throw new ResourceNotFoundException("Catergory not found");});


    }
}
