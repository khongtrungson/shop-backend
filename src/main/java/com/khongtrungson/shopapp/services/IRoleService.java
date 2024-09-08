package com.khongtrungson.shopapp.services;

import com.khongtrungson.shopapp.dtos.requests.RoleDTO;
import com.khongtrungson.shopapp.entities.Role;

import java.util.List;

public interface IRoleService {


    void createRole(RoleDTO role);

    void deleteRole(long id);

    List<Role> findAll();

    void updateRole(long id,RoleDTO roleDTO);
}
