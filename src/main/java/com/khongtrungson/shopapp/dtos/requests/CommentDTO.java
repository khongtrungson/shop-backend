package com.khongtrungson.shopapp.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CommentDTO {
    @Schema(example = "hàng siêu chất lượng")
    private String content;
    @Schema(example  = "5")
    @Min(value = 0,message = "rating must be from 1 to 5")
    @Max(value = 5,message = "rating must be from 1 to 5")
    private int rating;
}
