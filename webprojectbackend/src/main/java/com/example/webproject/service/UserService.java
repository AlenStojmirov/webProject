package com.example.webproject.service;

import com.example.webproject.model.Role;
import com.example.webproject.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    List<User> getAllUsers();

    Optional<User> findById(long id);

    User updateUser(long id, String username, String password,String email, List<Role> roles);

    void deleteUser(long id);

}
