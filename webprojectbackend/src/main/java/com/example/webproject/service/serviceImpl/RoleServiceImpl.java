package com.example.webproject.service.serviceImpl;

import com.example.webproject.model.Role;
import com.example.webproject.model.User;
import com.example.webproject.model.exception.InvalidRoleException;
import com.example.webproject.model.exception.ObjectExistException;
import com.example.webproject.repository.RoleRepository;
import com.example.webproject.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        try {
            return this.roleRepository.save(role);
        } catch (Exception e) {
            throw new ObjectExistException("This role already exist");
        }
    }

    @Override
    public List<Role> getAllRoles() {
        return this.roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(long id) {
        return this.roleRepository.findById(id);
    }

    @Override
    public Role updateRole(long id, String name, List<User> users) {
        Role role = this.roleRepository.findById(id).orElseThrow(InvalidRoleException::new);
        role.setName(name);
        role.setUsers(users);

        return this.roleRepository.save(role);
    }

    @Override
    public void deleteRole(long id) {
        this.roleRepository.deleteById(id);
    }
}