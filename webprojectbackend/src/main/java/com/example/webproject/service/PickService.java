package com.example.webproject.service;

import com.example.webproject.model.Pick;

import java.util.List;
import java.util.Optional;

public interface PickService {
    Pick save(Pick pick,String username);

    List<Pick> getAllPicks();

    Optional<Pick> findById(long id);

    Pick updatePick(long id, String info, String status_private_public);

    void deletePick(long id);

    List<Pick> getAllPicksWithStatusNull();
}
