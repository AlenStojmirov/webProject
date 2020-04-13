package com.example.webproject.repository;

import com.example.webproject.model.SportMatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<SportMatch,Long> {
}
