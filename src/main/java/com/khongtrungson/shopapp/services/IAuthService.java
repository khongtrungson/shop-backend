package com.khongtrungson.shopapp.services;

import com.khongtrungson.shopapp.dtos.requests.LoginInfo;
import com.khongtrungson.shopapp.dtos.requests.RefreshToken;
import com.khongtrungson.shopapp.dtos.responses.TokenResponse;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface IAuthService {
    TokenResponse login(LoginInfo loginInfo);

    TokenResponse refresh(RefreshToken refreshToken);

    TokenResponse socialLogin(OAuth2AuthenticationToken token);
}
