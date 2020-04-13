package com.example.webproject.service;

import com.example.webproject.model.Club;
import com.example.webproject.model.Sport;

import java.util.List;
import java.util.Optional;

public interface ClubService {

    Club save(Club club);

    List<Club> getAllClubs();

    Optional<Club> findById(long id);

    Club updateClub(long id, String name, int year,String country, String individual, Sport sport);

    void deleteClub(long id);

}
