package com.example.webproject.service.serviceImpl;

import com.example.webproject.model.Club;
import com.example.webproject.model.SportMatch;
import com.example.webproject.model.exception.InvalidMatchException;
import com.example.webproject.model.exception.ObjectExistException;
import com.example.webproject.repository.MatchRepository;
import com.example.webproject.service.MatchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;

    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public SportMatch save(SportMatch match) {
        long num = this.matchRepository.findAll().stream().filter(
                match1 -> match1.getName().equals(match.getName())
                && match1.getTime().equals(match.getTime())
        ).count();

        if (num > 0)
            throw new ObjectExistException("This match already exist");

        return this.matchRepository.save(match);
    }

    @Override
    public List<SportMatch> getAllMatches() {
        return this.matchRepository.findAll();
    }

    @Override
    public Optional<SportMatch> findById(long id) {
        return this.matchRepository.findById(id);
    }

    @Override
    public SportMatch updateMatch(long id, String name, String status, String time, Club clubHome, Club clubAway) {
        SportMatch match = this.matchRepository.findById(id).orElseThrow(InvalidMatchException::new);
        match.setName(name);
        match.setStatus(status);
        match.setTime(time);
        match.setClubHome(clubHome);
        match.setClubAway(clubAway);
        return this.matchRepository.save(match);
    }

    @Override
    public void deleteMatch(long id) {
        this.matchRepository.deleteById(id);
    }
}
