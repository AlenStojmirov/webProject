package com.example.webproject.repository;

import com.example.webproject.model.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League,Long> {
}
