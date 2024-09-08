package com.khongtrungson.shopapp.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khongtrungson.shopapp.entities.Product;
import com.khongtrungson.shopapp.entities.ProductImage;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    @JsonProperty("id")
    private Long id;
    private String name;
    private Float price;
    private String description;
    private long quantity;

    @JsonProperty("product_images")
    private List<ProductImage> productImages = new ArrayList<>();

}
