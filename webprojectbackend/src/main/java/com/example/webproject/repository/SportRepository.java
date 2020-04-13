package com.example.webproject.repository;

import com.example.webproject.model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportRepository extends JpaRepository<Sport,Long> {
}
