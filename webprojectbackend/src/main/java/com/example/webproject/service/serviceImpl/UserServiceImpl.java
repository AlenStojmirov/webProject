package com.example.webproject.service.serviceImpl;

import com.example.webproject.model.Role;
import com.example.webproject.model.Statistic;
import com.example.webproject.model.User;
import com.example.webproject.model.exception.InvalidUserException;
import com.example.webproject.model.exception.ObjectExistException;
import com.example.webproject.repository.RoleRepository;
import com.example.webproject.repository.StatisticRepository;
import com.example.webproject.repository.UserRepository;
import com.example.webproject.service.StatisticService;
import com.example.webproject.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final StatisticService statisticService;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, StatisticService statisticService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.statisticService = statisticService;
    }

    @Override
    public User save(User user) {

        long num = this.userRepository.findAll().stream().filter(user1 -> user1.getUsername().equals(user.getUsername())).count();
        long num1 = this.userRepository.findAll().stream().filter(user1 -> user1.getEmail().equals(user.getEmail())).count();
        if(num > 0 && num1 > 0)
            throw new ObjectExistException("Your username and email already exist");
        else if(num > 0)
            throw new ObjectExistException("Your username already exist");
        else if(num1 > 0)
            throw  new ObjectExistException("Your email already exist");

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Role role = roleRepository.findById(2L).get();
        user.setRoles(Collections.singletonList(role));
        role.setUsers(Collections.singletonList(user));
        user.setStatisticId(statisticService.save(new Statistic()));

        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findById(long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public User updateUser(long id, String username, String password, String email, List<Role> roles) {
        User user = this.userRepository.findById(id).orElseThrow(InvalidUserException::new);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRoles(roles);
        return this.userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public List<User> theBest5TipsterByProfit() {
        return userRepository.theBest5TipsterByProfit();
    }

    @Override
    public List<User> theBest5TipstersByWinRatio() {
        return userRepository.theBest5TipstersByWinRatio();
    }

    @Override
    public List<User> theBest5TipsterByWinRatioInLast30Days() {
        return userRepository.theBest5TipsterByWinRatioInLast30Days();
    }

    @Override
    public List<User> theBest5TipsterByProfitInLast30Days() {
        return userRepository.theBest5TipsterByProfitInLast30Days();
    }
}
