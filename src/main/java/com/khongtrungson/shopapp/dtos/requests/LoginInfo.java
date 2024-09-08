package com.khongtrungson.shopapp.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginInfo {
    @JsonProperty(value = "phone_number")
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private String password;
}
