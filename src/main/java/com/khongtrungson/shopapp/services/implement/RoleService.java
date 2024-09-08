package com.khongtrungson.shopapp.services.implement;

import com.khongtrungson.shopapp.dtos.requests.RoleDTO;
import com.khongtrungson.shopapp.entities.Role;
import com.khongtrungson.shopapp.exceptions.DataNotFoundException;
import com.khongtrungson.shopapp.mappers.RoleMapper;
import com.khongtrungson.shopapp.repositories.RoleRepository;
import com.khongtrungson.shopapp.services.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;
    @Override
    public void createRole(RoleDTO role) {
        roleRepository.save(RoleMapper.dtoToEntity(role));
    }

    @Override
    public void deleteRole(long id) {
        Role role = roleRepository.findById(id).orElseThrow(()-> new DataNotFoundException("role does not exist"));
        roleRepository.delete(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void updateRole(long id,RoleDTO roleDTO) {
        Role role = roleRepository.findById(id).orElseThrow(()-> new DataNotFoundException("role does not exist"));
        role.setName(role.getName());
        roleRepository.save(role);
    }
}
