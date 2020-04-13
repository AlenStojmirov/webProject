package com.example.webproject.repository;

import com.example.webproject.model.Pick;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PickRepository extends JpaRepository<Pick,Long> {
}
