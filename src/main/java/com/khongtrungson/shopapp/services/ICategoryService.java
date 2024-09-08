package com.khongtrungson.shopapp.services;

import com.khongtrungson.shopapp.dtos.requests.CategoryDTO;
import com.khongtrungson.shopapp.dtos.responses.CategoryResponse;
import com.khongtrungson.shopapp.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService {
    Category createCategory(CategoryDTO categoryDTO);
    CategoryResponse getCategoryById(long id) ;
    List<CategoryResponse> getAllCategories();
    Category updateCategory(long id, CategoryDTO categoryDTO) ;
    void deleteCategory(long id);
}
