package com.example.webproject.service;

import com.example.webproject.model.Sport;

import java.util.List;
import java.util.Optional;

public interface SportService {

    Sport save(Sport sport);

    List<Sport> getAllSports();

    Optional<Sport> findById(long id);

    Sport updateSport(long id, String name);

    void deleteSport(long id);
}
