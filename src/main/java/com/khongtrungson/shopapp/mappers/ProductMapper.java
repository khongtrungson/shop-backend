package com.khongtrungson.shopapp.mappers;

import ch.qos.logback.core.util.StringUtil;
import com.khongtrungson.shopapp.dtos.requests.ProductDTO;
import com.khongtrungson.shopapp.dtos.responses.ProductImageResponse;
import com.khongtrungson.shopapp.dtos.responses.ProductResponse;
import com.khongtrungson.shopapp.entities.Category;
import com.khongtrungson.shopapp.entities.Product;
import com.khongtrungson.shopapp.entities.ProductImage;

import java.util.List;

public class ProductMapper {
    public static Product dtoToEntity(ProductDTO productDTO, Category category){
        Product product = new Product();
        product.setCategory(category);
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        return product;
    }

    public static  ProductResponse entityToResponse(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setId(product.getId());
        productResponse.setProductImages(product.getProductImages());
        productResponse.setQuantity(product.getQuantity());
        return productResponse;
    }
    public static Product updateEntity(ProductDTO productDTO,Product product,Category category) {
        if(productDTO.getCategoryId()>0){
            product.setCategory(category);
        }
        if(StringUtil.notNullNorEmpty(productDTO.getDescription())){
            product.setDescription(productDTO.getDescription());
        }
        if(StringUtil.notNullNorEmpty(productDTO.getName())){
            product.setName(productDTO.getName());
        }
        if(productDTO.getPrice()>0){
            product.setPrice(productDTO.getPrice());
        }
        if(productDTO.getQuantity()> 0){
            product.setQuantity(product.getQuantity());
        }

        return product;
    }
}
