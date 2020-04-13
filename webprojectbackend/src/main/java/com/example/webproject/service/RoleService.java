package com.example.webproject.service;

import com.example.webproject.model.Role;
import com.example.webproject.model.User;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Role save(Role role);

    List<Role> getAllRoles();

    Optional<Role> findById(long id);

    Role updateRole(long id, String name, List<User> users);

    void deleteRole(long id);

}
