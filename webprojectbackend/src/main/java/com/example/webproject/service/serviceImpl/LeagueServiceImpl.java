package com.example.webproject.service.serviceImpl;

import com.example.webproject.model.League;
import com.example.webproject.model.Sport;
import com.example.webproject.model.exception.InvalidLeagueException;
import com.example.webproject.model.exception.ObjectExistException;
import com.example.webproject.repository.LeagueRepository;
import com.example.webproject.service.LeagueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeagueServiceImpl implements LeagueService {

    private final LeagueRepository leagueRepository;

    public LeagueServiceImpl(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @Override
    public League save(League league) {

        long num = this.leagueRepository.findAll()
                .stream().filter(league1 -> league1.getName().equals(league.getName())
                && league1.getSport().getName().equals(league.getSport().getName())).count();
        if(num != 0)
            throw new ObjectExistException("League already exist");

        return this.leagueRepository.save(league);
    }

    @Override
    public List<League> getAllLeagues() {
        return this.leagueRepository.findAll();
    }

    @Override
    public Optional<League> findById(long id) {
        return this.leagueRepository.findById(id);
    }

    @Override
    public League updateLeague(long id, String name, Sport sport) {
        League league = this.leagueRepository.findById(id).orElseThrow(InvalidLeagueException::new);

        league.setName(name);
        league.setSport(sport);

        return this.leagueRepository.save(league);
    }

    @Override
    public void deleteLeague(long id) {
        this.leagueRepository.deleteById(id);
    }
}
