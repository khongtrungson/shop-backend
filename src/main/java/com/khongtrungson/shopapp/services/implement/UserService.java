package com.khongtrungson.shopapp.services.implement;


import com.khongtrungson.shopapp.dtos.requests.InsertUserRequest;
import com.khongtrungson.shopapp.dtos.requests.UserUpdateRequest;
import com.khongtrungson.shopapp.dtos.responses.PageResponse;

import com.khongtrungson.shopapp.dtos.responses.UserResponse;
import com.khongtrungson.shopapp.entities.Role;
import com.khongtrungson.shopapp.entities.User;
import com.khongtrungson.shopapp.exceptions.DataNotFoundException;
import com.khongtrungson.shopapp.exceptions.InvalidParamException;
import com.khongtrungson.shopapp.mappers.UserMapper;
import com.khongtrungson.shopapp.repositories.RoleRepository;
import com.khongtrungson.shopapp.repositories.UserRepository;
import com.khongtrungson.shopapp.repositories.criteria.SearchUserRepository;
import com.khongtrungson.shopapp.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final SearchUserRepository searchUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    @Override
    public PageResponse<List<UserResponse>> searchUsers(int pageNo, int pageSize, String sortBy, String... search) {
        return searchUserRepository.searchByCriteria(pageNo,pageSize,sortBy,search);
    }

    @Override
    public void createUser(InsertUserRequest userRequest) {
        if(userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())){
            throw new InvalidParamException("phone number is already exist");
        }
        Role role = roleRepository.findById(Long.valueOf(1)).orElseThrow(()->new DataNotFoundException("role is not exist"));
        String hashPassword = passwordEncoder.encode(userRequest.getPassword());
        User user = UserMapper.userResquestToUser(userRequest,hashPassword,role);
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserUpdateRequest userRequest, long id) {
        User user = userRepository.findById(id).orElseThrow(()->new DataNotFoundException("user not found"));
       Optional<User> nUser= userRepository.findUserByPhoneNumber(userRequest.getPhoneNumber());
       if(nUser.isPresent()){
           throw new InvalidParamException("phoneNumber is already exist");
       }
        User newUser = UserMapper.updateToEntity(userRequest,user,passwordEncoder);
        userRepository.save(newUser);
    }


    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public PageResponse<List<UserResponse>> findAllUsers(Pageable pageable) {
        //lay ra page user
        Page<User> users = userRepository.findAll(pageable);
        // convert page to pageResponse
        List<UserResponse> userResponses=users.stream().map((user)->UserMapper.userToUserResponse(user)).collect(Collectors.toList());
        return PageResponse.<List<UserResponse>>builder()
                .size(users.getSize())
                .total(users.getTotalPages())
                .items(userResponses)
                .build();
    }

    @Override
    public UserResponse findById(long id) {
        var user = userRepository.findById(id).orElseThrow(()->new DataNotFoundException("user not found"));
        return UserMapper.userToUserResponse(user);
    }
}
