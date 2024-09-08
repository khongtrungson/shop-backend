package com.khongtrungson.shopapp.mappers;

import com.khongtrungson.shopapp.dtos.requests.CategoryDTO;
import com.khongtrungson.shopapp.dtos.responses.CategoryResponse;
import com.khongtrungson.shopapp.entities.Category;

public class CategoryMapper {
    public static  Category dtoToEntity(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return category;
    }
    public static CategoryResponse entityToResponse(Category category){
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setName(category.getName());
        categoryResponse.setId(category.getId());
        return categoryResponse;
    }
    public static Category updateEntity(CategoryDTO categoryDTO,Category category){
       category.setName(categoryDTO.getName());
        return category;
    }
}
