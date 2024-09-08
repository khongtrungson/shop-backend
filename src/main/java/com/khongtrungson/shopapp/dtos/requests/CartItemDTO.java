package com.khongtrungson.shopapp.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CartItemDTO {
    @Min(value = 1,message = "product id must be greater than 1")
    @Schema(example = "1")
    private long productId;
    @Min(value = 1,message = "quantity must be greater than 1")
    @Schema(example = "2")
    private long quantity;
}
