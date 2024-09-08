package com.khongtrungson.shopapp.controllers;

import com.khongtrungson.shopapp.dtos.requests.LoginInfo;
import com.khongtrungson.shopapp.dtos.requests.RefreshToken;
import com.khongtrungson.shopapp.dtos.responses.ResponseData;
import com.khongtrungson.shopapp.dtos.responses.TokenResponse;
import com.khongtrungson.shopapp.services.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Authencation APIs"
)
@RestController
@RequestMapping(value = "/auth",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;
    @PostMapping("/login")
    @Operation(summary = "login with username and password")
    public ResponseData<TokenResponse> login(@Valid @RequestBody LoginInfo loginInfo){
        TokenResponse token =   authService.login(loginInfo);
        return ResponseData.<TokenResponse>builder()
                .data(token)
                .message("login successfully")
                .status(HttpStatus.OK.value())
                .build();
    }
    @Operation(summary = "using refresh token to exchange for access token")
    @PostMapping("/refresh")
    public ResponseData<TokenResponse> refresh(@Valid @RequestBody RefreshToken refreshToken){
        TokenResponse token =   authService.refresh(refreshToken);

        return ResponseData.<TokenResponse>builder()
                .data(token)
                .message("login successfully")
                .status(HttpStatus.OK.value())
                .build();
    }

    @GetMapping()
    public ResponseData<?> logout(){
        return null;
    }
}
