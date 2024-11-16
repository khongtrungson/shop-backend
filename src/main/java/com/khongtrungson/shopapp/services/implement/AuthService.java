package com.khongtrungson.shopapp.services.implement;

import com.khongtrungson.shopapp.config.CustomAuthProvider;
import com.khongtrungson.shopapp.config.CustomUserDetailService;
import com.khongtrungson.shopapp.dtos.requests.LoginInfo;
import com.khongtrungson.shopapp.dtos.requests.RefreshToken;
import com.khongtrungson.shopapp.dtos.responses.TokenResponse;
import com.khongtrungson.shopapp.entities.User;
import com.khongtrungson.shopapp.exceptions.InvalidParamException;
import com.khongtrungson.shopapp.repositories.UserRepository;
import com.khongtrungson.shopapp.services.IAuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Profile("!prod")
public class AuthService implements IAuthService {
    private final AuthenticationManager manager;
    private final UserRepository userRepository;
    @Value("${key.secretKey}")
    private String key ;
    @Value("${key.refreshKey}")
    private String refreshKey;

    @Override
    public TokenResponse login(LoginInfo loginInfo) {

        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(loginInfo.getPhoneNumber(),
                loginInfo.getPassword());
        Authentication authenticationResponse = manager.authenticate(authentication);
        try {
            String jwt ;
            String refreshToken ;
            if (null != authenticationResponse && authenticationResponse.isAuthenticated()) {
                SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
                SecretKey secretRefreshKey = Keys.hmacShaKeyFor(refreshKey.getBytes(StandardCharsets.UTF_8));
                jwt = generateAccessToken(secretKey,authenticationResponse);
                refreshToken = generateRefreshToken(secretRefreshKey, authenticationResponse);
                return new TokenResponse(jwt,refreshToken);
            }
        }catch (Exception ex){
            throw new InvalidParamException("invalid phonenumber or password ");
        }
        return null;
    }
    private String generateAccessToken(SecretKey secretKey,Authentication authenticationResponse){
        return Jwts.builder().setIssuer("kts").setSubject("JWT token")
                .claim("id",authenticationResponse.getName())
                .claim("authorities", authenticationResponse.getAuthorities().stream().map(
                        GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 100000))
                .signWith(secretKey).compact();

    }
    private String generateRefreshToken(SecretKey secretKey,Authentication authenticationResponse){
        return Jwts.builder().setIssuer("kts")
                .claim("id",authenticationResponse.getName())
                .claim("authorities", authenticationResponse.getAuthorities().stream().map(
                        GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                .setSubject("Refresh token")
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 30000000))
                .signWith(secretKey).compact();
    }

    @Override
    public  TokenResponse refresh(RefreshToken refreshToken) {
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
        SecretKey secretRefreshKey = Keys.hmacShaKeyFor(refreshKey.getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.parser().setSigningKey(secretRefreshKey)
                .parseClaimsJws(refreshToken.getRefreshToken())
                .getBody();
        String jwt = Jwts.builder().setIssuer("kts").setSubject("JWT token")
                .claim("id",claims.get("id"))
                .claim("authorities", claims.get("authorities"))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 100000))
                .signWith(secretKey).compact();
        return new TokenResponse(jwt,refreshToken.getRefreshToken());
    }

    @Override
    public TokenResponse socialLogin(OAuth2AuthenticationToken token) {
        OidcUser principal = (OidcUser) token.getPrincipal();
        // principal is not exist, when it's email and type differ with user that retrieved from database

        User user = userRepository.findUserByEmail(principal.getEmail());

        if(user == null
                || !(user.getEmail().equals(principal.getEmail()) && user.getType().equals(token.getAuthorizedClientRegistrationId()))) {
            // the user is not exist go into this block

            // create the user
            User newUser = new User();
            userRepository.save(newUser);
        }
        // generate token
        return null;
    }
}
