package com.example.webproject.service;

import com.example.webproject.model.League;
import com.example.webproject.model.Sport;

import java.util.List;
import java.util.Optional;

public interface LeagueService {

    League save(League league);

    List<League> getAllLeagues();

    Optional<League> findById(long id);

    League updateLeague(long id, String name, Sport sport);

    void deleteLeague(long id);

}
