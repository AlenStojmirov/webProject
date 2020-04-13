package com.example.webproject.repository;

import com.example.webproject.model.PrivateUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivateUserRepository extends JpaRepository<PrivateUser,Long> {
}
