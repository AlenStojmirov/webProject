package com.example.webproject.service.serviceImpl;

import com.example.webproject.model.PrivateUser;
import com.example.webproject.model.exception.InvalidPrivateUserException;
import com.example.webproject.repository.PrivateUserRepository;
import com.example.webproject.service.PrivateUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrivateUserServiceImpl implements PrivateUserService {

    private final PrivateUserRepository privateUserRepository;

    public PrivateUserServiceImpl(PrivateUserRepository privateUserRepository) {
        this.privateUserRepository = privateUserRepository;
    }

    @Override
    public PrivateUser save(PrivateUser privateUser) {
        return this.privateUserRepository.save(privateUser);
    }

    @Override
    public List<PrivateUser> getAllPrivateUsers() {
        return this.privateUserRepository.findAll();
    }

    @Override
    public Optional<PrivateUser> findById(long id) {
        return this.privateUserRepository.findById(id);
    }

    @Override
    public PrivateUser updatePrivateUser(long id, int costSubscribe) {
        PrivateUser privateUser = privateUserRepository.findById(id).orElseThrow(InvalidPrivateUserException::new);
        privateUser.setCostSubscribe(costSubscribe);

        return this.privateUserRepository.save(privateUser);
    }

    @Override
    public void deletePrivateUser(long id) {
        this.privateUserRepository.deleteById(id);
    }
}
