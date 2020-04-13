package com.example.webproject.service;

import com.example.webproject.model.Club;
import com.example.webproject.model.League;
import com.example.webproject.model.SportMatch;

import java.util.List;
import java.util.Optional;

public interface MatchService {
    SportMatch save(SportMatch match);

    List<SportMatch> getAllMatches();

    Optional<SportMatch> findById(long id);

    SportMatch updateMatch(long id, String name, String status, String time, Club clubHome, Club clubAway);

    void deleteMatch(long id);
}
