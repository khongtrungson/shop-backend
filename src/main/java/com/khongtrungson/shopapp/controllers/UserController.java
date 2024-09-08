package com.khongtrungson.shopapp.controllers;
import com.khongtrungson.shopapp.dtos.requests.InsertUserRequest;
import com.khongtrungson.shopapp.dtos.requests.UserUpdateRequest;
import com.khongtrungson.shopapp.dtos.responses.PageResponse;
import com.khongtrungson.shopapp.dtos.responses.ResponseData;
import com.khongtrungson.shopapp.dtos.responses.UserResponse;
import com.khongtrungson.shopapp.services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
   name = "CRUD APis for User"
)
@RestController
@RequestMapping(value = "/user",produces =  MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class UserController {
    private final IUserService userService ;
    @Operation(summary = "search a page users ")
    @GetMapping("")
    public ResponseData<PageResponse<List<UserResponse>>> searchUsers(
            @RequestParam(defaultValue = "1", required = false) @Positive int pageNo,
            @RequestParam(defaultValue = "10", required = false) @Positive int pageSize,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "") String... search) throws Exception{

        var pageResponse = userService.searchUsers(pageNo,pageSize,sortBy,search);
        return ResponseData.<PageResponse<List<UserResponse>>>builder()
                .status(HttpStatus.OK.value())
                .message("page users here")
                .data(pageResponse)
                .build();
    }
    @Operation(summary = "create an user api ")
    @PostMapping()
    public ResponseData<String> createUser(@RequestBody @Valid InsertUserRequest userRequest) throws Exception{
        userService.createUser(userRequest);
         return  ResponseData.<String>builder()
                 .data("create user successfully")
                 .message("create user")
                 .status(HttpStatus.CREATED.value())
                 .build();
    }
    @Operation(summary = "delete an user has id ")
    @DeleteMapping("/{id}")
    public ResponseData<String> deleteUser(@PathVariable @Positive int id){
        userService.deleteUser(id);
        return  ResponseData.<String>builder()
                .data("delete user successfully")
                .message("delete user action")
                .status(HttpStatus.CREATED.value())
                .build();
    }
    @Operation(summary = "update an user has id ")
    @PutMapping("/{id}")
    public ResponseData<String> updateUser(@PathVariable @Positive int id,
                                           @Valid @RequestBody UserUpdateRequest userUpdateRequest){
        userService.updateUser(userUpdateRequest,id);
        return  ResponseData.<String>builder()
                .data("update user successfully")
                .message("update user action")
                .status(HttpStatus.CREATED.value())
                .build();
    }

    @Operation(summary = "get an user has id ")
    @GetMapping("/{id}")
    public ResponseData<UserResponse> getUser(@PathVariable @Positive long id){
        var userResponse = userService.findById(id);
        return  ResponseData.<UserResponse>builder()
                .data(userResponse)
                .message("user has id: "+id)
                .status(HttpStatus.CREATED.value())
                .build();
    }


}
