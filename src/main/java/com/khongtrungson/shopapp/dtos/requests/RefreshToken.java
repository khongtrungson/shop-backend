package com.khongtrungson.shopapp.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RefreshToken {
    @JsonProperty("refresh_token")
    @NotEmpty
    private String refreshToken;
}
