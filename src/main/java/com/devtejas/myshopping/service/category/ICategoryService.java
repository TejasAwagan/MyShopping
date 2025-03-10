package com.devtejas.myshopping.service.category;

import com.devtejas.myshopping.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoryService {

    Category getCategoryById(Long id);

    Category getCategoryByName(String name);

    List<Category> getAllCategory();

    Category addCategory(Category category);

    Category updateCategory(Category category, Long id);

    void deleteCategoryById (Long id);

}
