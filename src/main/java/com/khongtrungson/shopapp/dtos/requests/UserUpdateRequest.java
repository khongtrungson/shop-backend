package com.khongtrungson.shopapp.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.khongtrungson.shopapp.dtos.validators.FieldMatch;
import com.khongtrungson.shopapp.dtos.validators.PhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldMatch.List(
        @FieldMatch(field = "password",fieldMatch = "retypePassword")
)
public class UserUpdateRequest {

    @Schema(example = "khổng trung sơn")
    @JsonProperty("fullname")
    private String fullName;

    @Schema(example="123-456-7890")
    @JsonProperty("phone_number")
    @PhoneNumber
    private String phoneNumber;

    @Schema(example = "vĩnh phúc province")
    @JsonProperty("address")
    private String address;

    @JsonProperty("password")
    @Schema(example="kaitokid1412")
    private String password;

    @Schema(example="kaitokid1412")
    @JsonProperty("retype_password")
    private String retypePassword;

    @JsonProperty("date_of_birth")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date dateOfBirth;
}
