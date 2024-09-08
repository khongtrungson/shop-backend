package com.khongtrungson.shopapp.services;

import com.khongtrungson.shopapp.dtos.requests.ProductDTO;
import com.khongtrungson.shopapp.dtos.responses.PageResponse;
import com.khongtrungson.shopapp.dtos.responses.ProductResponse;
import com.khongtrungson.shopapp.entities.Product;
import org.hibernate.query.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    Product createProduct(ProductDTO productDTO) throws IOException;

    ProductResponse getProductById(long id);

    Product updateProduct(long id, ProductDTO productDTO);

    void deleteProduct(long id);

    boolean existsByName(String name);

    boolean createProductImages(Long productId, List<MultipartFile> files) throws IOException;

    List<ProductResponse> findProductsByIds(List<Long> productIds);

    PageResponse<List<ProductResponse>> searchProduct(int pageNo, int pageSize, String sortBy, String[] search);

    List<ProductResponse> findAllProducts();
}