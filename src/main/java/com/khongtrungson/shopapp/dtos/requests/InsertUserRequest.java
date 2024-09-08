package com.khongtrungson.shopapp.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.khongtrungson.shopapp.dtos.validators.FieldMatch;
import com.khongtrungson.shopapp.dtos.validators.PhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@FieldMatch.List({
        @FieldMatch(field = "password",fieldMatch = "retypePassword")
})
public class InsertUserRequest {
    @Schema(example = "khổng trung sơn")
    @NotEmpty
    @JsonProperty("fullname")
    private String fullName;

    @Schema(example = "0123456789")
    @JsonProperty("phone_number")
    @PhoneNumber()
    @NotEmpty
    private String phoneNumber;

    @Schema(example = "...vĩnh phúc")
    @JsonProperty("address")
    @NotEmpty
    private String address;

    @NotBlank(message = "Password cannot be blank")
    @Schema(example = "kaitokid1412")
    @NotEmpty
    private String password;

    @JsonProperty("retype_password")
    @Schema(example = "kaitokid1412")
    @NotEmpty
    private String retypePassword;


    @JsonProperty("date_of_birth")
    @NotNull(message = "dateOfBirth must be not null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    @Schema(example = "02/15/2000",description = "MM/DD/YYYY format")
    private Date dateOfBirth;


}
