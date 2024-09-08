package com.khongtrungson.shopapp.controllers;

import com.khongtrungson.shopapp.dtos.requests.CategoryDTO;
import com.khongtrungson.shopapp.dtos.responses.CategoryResponse;
import com.khongtrungson.shopapp.dtos.responses.ResponseData;
import com.khongtrungson.shopapp.exceptions.DataNotFoundException;
import com.khongtrungson.shopapp.services.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@Tag(
        name = "CRUD APIs for Category"
)
@RestController
@RequestMapping(value = "/category",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class CategoryController {
    private final ICategoryService categoryService;


    // Show all of categories
    @Operation(summary = "create category api")
    @PostMapping("")
    public ResponseData<String> createCategory(
            @Valid @RequestBody CategoryDTO categoryDTO
    ) {
        categoryService.createCategory(categoryDTO);
        return ResponseData.<String>builder()
                .message("create category")
                .status(201)
                .data("create category successfully")
                .build();

    }
    @Operation(summary = "get all categories api")
    @GetMapping("") // http://localhost:8088/api/v1/categories
    public ResponseData<List<CategoryResponse>> getAllCategories() {
        var categories = categoryService.getAllCategories();
        return ResponseData.<List<CategoryResponse>>builder()
                .data(categories)
                .status(HttpStatus.OK.value())
                .message("categories here")
                .build();
    }
    @Operation(summary = "update category api")
    @PutMapping("/{id}")
    public ResponseData<String> updateCategory(
             @PathVariable @Positive Long id
            ,@Valid @RequestBody CategoryDTO categoryDTO) throws DataNotFoundException {
        categoryService.updateCategory(id,
                categoryDTO);
        return ResponseData.<String>builder()
                .message("update category action")
                .data("update successfully")
                .status(HttpStatus.OK.value())
                .build();
    }
    @Operation(summary = "delete category api")
    @DeleteMapping("/{id}")
    public ResponseData<String> deleteCategory(@PathVariable @Positive Long id
                                          ) throws DataNotFoundException {
        categoryService.deleteCategory(id);
        return ResponseData.<String>builder()
                .data("delete successfully")
                .message("delete action")
                .status(HttpStatus.OK.value())
                .build();
    }
    @Operation(summary = "get category api by category'id")
    @GetMapping("/{id}")
    public ResponseData<CategoryResponse> getCategoryById(
           @Positive @PathVariable("id") Long id
    ) throws DataNotFoundException {
        var category = categoryService.getCategoryById(id);
        return ResponseData.<CategoryResponse>builder()
                .status(HttpStatus.OK.value())
                .message("category has id: "+id)
                .data(category)
                .build();
    }

    @GetMapping("/get-categories-by-keyword")
    public ResponseData<?> getCategoriesByKeyword(
            @RequestParam(defaultValue = "", required = false) String keyword,
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        return null;

    }
}
