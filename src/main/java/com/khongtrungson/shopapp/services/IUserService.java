package com.khongtrungson.shopapp.services;



import com.khongtrungson.shopapp.dtos.requests.InsertUserRequest;
import com.khongtrungson.shopapp.dtos.requests.UserUpdateRequest;
import com.khongtrungson.shopapp.dtos.responses.PageResponse;
import com.khongtrungson.shopapp.dtos.responses.UserResponse;
import com.khongtrungson.shopapp.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {

    PageResponse<List<UserResponse>> searchUsers(int pageNo, int pageSize, String sortBy, String... search) throws Exception;

    void createUser(InsertUserRequest userRequest) throws Exception;



    void updateUser(UserUpdateRequest userRequest,long id);



    void deleteUser(long id);

    PageResponse<List<UserResponse>> findAllUsers(Pageable pageable);

    UserResponse findById(long id);
}
