package com.khongtrungson.shopapp.services;

import com.khongtrungson.shopapp.dtos.requests.LoginInfo;
import com.khongtrungson.shopapp.dtos.requests.RefreshToken;
import com.khongtrungson.shopapp.dtos.responses.TokenResponse;

public interface IAuthService {
    TokenResponse login(LoginInfo loginInfo);

    TokenResponse refresh(RefreshToken refreshToken);
}
