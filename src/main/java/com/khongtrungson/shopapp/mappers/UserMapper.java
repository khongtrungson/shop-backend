package com.khongtrungson.shopapp.mappers;

import ch.qos.logback.core.util.StringUtil;
import com.khongtrungson.shopapp.dtos.requests.InsertUserRequest;
import com.khongtrungson.shopapp.dtos.requests.UserUpdateRequest;
import com.khongtrungson.shopapp.dtos.responses.UserResponse;
import com.khongtrungson.shopapp.entities.Role;
import com.khongtrungson.shopapp.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {
    public static UserResponse userToUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setActive(user.isActive());
        userResponse.setAddress(user.getAddress());
        userResponse.setFullName(user.getFullName());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setDateOfBirth(user.getDateOfBirth());
        return userResponse;
    }

    public static User userResquestToUser(InsertUserRequest userRequest,String hash,Role role){
        User user = new User();
        user.setActive(true);
        user.setAddress(userRequest.getAddress());
        user.setFullName(userRequest.getFullName());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setDateOfBirth(userRequest.getDateOfBirth());
        user.setPassword(hash);
        user.setRole(role);
        return user;
    }
    public static User updateToEntity(UserUpdateRequest userUpdateRequest, User user, PasswordEncoder passwordEncoder){
        if(StringUtil.notNullNorEmpty(userUpdateRequest.getAddress())){
            user.setAddress(userUpdateRequest.getAddress());
        }
        if(StringUtil.notNullNorEmpty(userUpdateRequest.getFullName())){
            user.setFullName(userUpdateRequest.getFullName());
        }
        if(StringUtil.notNullNorEmpty(userUpdateRequest.getPhoneNumber())){
            user.setFullName(userUpdateRequest.getPhoneNumber());
        }
        if(StringUtil.notNullNorEmpty(userUpdateRequest.getPassword())){
            user.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
        }

        user.setDateOfBirth(userUpdateRequest.getDateOfBirth());
        return user;
    }
}
