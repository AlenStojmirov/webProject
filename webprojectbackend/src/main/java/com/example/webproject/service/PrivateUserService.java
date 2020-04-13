package com.example.webproject.service;

import com.example.webproject.model.PrivateUser;

import java.util.List;
import java.util.Optional;

public interface PrivateUserService {

    PrivateUser save(PrivateUser privateUser);

    List<PrivateUser> getAllPrivateUsers();

    Optional<PrivateUser> findById(long id);

    PrivateUser updatePrivateUser(long id, int costSubscribe);

    void deletePrivateUser(long id);

}
