package com.khongtrungson.shopapp.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
public class ProductImageResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("image_url")
    private String imageUrl;
}
