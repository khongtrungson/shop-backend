package com.khongtrungson.shopapp.mappers;

import com.khongtrungson.shopapp.dtos.requests.RoleDTO;
import com.khongtrungson.shopapp.entities.Role;

public class RoleMapper {
    public static Role dtoToEntity(RoleDTO roleDTO){
        Role role = new Role();
        role.setName(roleDTO.getName());
        return role;
    }
}
