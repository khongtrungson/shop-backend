package com.khongtrungson.shopapp.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @Schema(example = "Ấm siêu tốc")
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 200, message = "Title must be between 3 and 200 characters")
    private String name;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    @Max(value = 10000000, message = "Price must be less than or equal to 10,000,000")
    @Schema(example = "100000")
    @NotNull
    private Float price;

    @Schema(example = "Hàng Việt Nam chất lượng cao ...")
    private String description;

    @Schema(example = "2000")
    @Min(value = 1)
    @NotNull
    private long quantity;
    @NotNull
    @Min(value = 0)
    @Schema(example = "2")
    private Long categoryId;
    @NotNull
    private List<MultipartFile> files = new ArrayList<>();


}
