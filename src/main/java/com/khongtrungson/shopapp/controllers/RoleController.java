package com.khongtrungson.shopapp.controllers;

import com.khongtrungson.shopapp.dtos.requests.RoleDTO;
import com.khongtrungson.shopapp.dtos.responses.ResponseData;
import com.khongtrungson.shopapp.entities.Role;
import com.khongtrungson.shopapp.services.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name = "CRUD REST APIs for Role "
)
@RestController
@RequestMapping(value = "/role",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class RoleController {
    private final IRoleService roleService;
    @Operation(summary = "create a role api")
    @PostMapping
    public ResponseData<String> createRole(@Valid @RequestBody RoleDTO role){
        roleService.createRole(role);
        return ResponseData.<String>builder()
                .status(HttpStatus.OK.value())
                .message("create role action")
                .data("create role successfully")
                .build();
    }
    @Operation(summary = "delete a role has id api")
    @DeleteMapping("/{id}")
    public ResponseData<String> deleteRole(@PathVariable @Positive long id){
        roleService.deleteRole(id);
      return  ResponseData.<String>builder()
                .status(HttpStatus.OK.value())
                .message("delete role action")
                .data("delete role successfully")
                .build();
    }
    @Operation(summary = "get all roles api")
    @GetMapping("/all")
    public ResponseData<List<Role>> findAllRole(){
        List<Role> roles = roleService.findAll();
        return ResponseData.<List<Role>>builder()
                .status(HttpStatus.OK.value())
                .message("delete role action")
                .data(roles)
                .build();
    }
    @Operation(summary = "update a role has id")
    @PutMapping("/{id}")
    public ResponseData<String> updateRole(
            @PathVariable @Positive long id,
            @RequestBody @Valid RoleDTO roleDTO){
        roleService.updateRole(id,roleDTO);
        return ResponseData.<String>builder()
                .status(HttpStatus.OK.value())
                .message("update role action")
                .data("update role successfully")
                .build();
    }
}
