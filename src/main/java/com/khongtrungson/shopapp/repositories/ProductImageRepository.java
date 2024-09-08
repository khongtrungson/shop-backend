package com.khongtrungson.shopapp.repositories;


import com.khongtrungson.shopapp.entities.Product;
import com.khongtrungson.shopapp.entities.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {
    List<ProductImage> findByProduct(Product product);
}
