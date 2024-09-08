package com.khongtrungson.shopapp.repositories;


import com.khongtrungson.shopapp.dtos.requests.CategoryDTO;
import com.khongtrungson.shopapp.entities.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category,Long> {

}
