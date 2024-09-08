package com.khongtrungson.shopapp.services.implement;

import com.khongtrungson.shopapp.dtos.requests.CategoryDTO;
import com.khongtrungson.shopapp.dtos.responses.CategoryResponse;
import com.khongtrungson.shopapp.entities.Category;
import com.khongtrungson.shopapp.exceptions.DataNotFoundException;
import com.khongtrungson.shopapp.mappers.CategoryMapper;
import com.khongtrungson.shopapp.repositories.CategoryRepository;
import com.khongtrungson.shopapp.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.dtoToEntity(categoryDTO);
        return categoryRepository.save(category);
    }

    @Override
    public CategoryResponse getCategoryById(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new DataNotFoundException("not exist category"));
        return CategoryMapper.entityToResponse(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        var categories = categoryRepository.findAll();
        var categoryResponses = categories.stream().map((category)->{
            return CategoryMapper.entityToResponse(category);
        }).collect(Collectors.toList());

        return categoryResponses;
    }

    @Override
    public Category updateCategory(long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new DataNotFoundException("not exist category"));

        return categoryRepository.save(CategoryMapper.updateEntity(categoryDTO,category));
    }

    @Override
    public void deleteCategory(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new DataNotFoundException("not exist category"));
        categoryRepository.delete(category);
    }
}
