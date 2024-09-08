package com.khongtrungson.shopapp.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDTO {
    @Schema(example = "1")
    @Min(value = 0,message = "user id must greater than 0")
    @NotNull
    private Long userId;

    @Schema(example = "giao nhanh lên")
    private String note;
    @Schema(example = "GIAO-HANG-NHANH")

    private String shippingMethod;
    @NotNull
    private List<CartItemDTO> cartItems;
}
