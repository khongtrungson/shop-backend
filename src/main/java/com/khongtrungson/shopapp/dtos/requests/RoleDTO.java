package com.khongtrungson.shopapp.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RoleDTO {
    @Schema(example = "ADMIN")
    @Pattern(regexp = "ADMIN|USER")
    @NotEmpty
    private String name;
}
