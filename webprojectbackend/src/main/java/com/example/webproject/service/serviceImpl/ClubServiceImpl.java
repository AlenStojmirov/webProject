package com.example.webproject.service.serviceImpl;

import com.example.webproject.model.Club;
import com.example.webproject.model.Sport;
import com.example.webproject.model.exception.ObjectExistException;
import com.example.webproject.model.exception.InvalidLeagueException;
import com.example.webproject.repository.ClubRepository;
import com.example.webproject.service.ClubService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;

    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public Club save(Club club) {
        long num = clubRepository.findAll().stream().
                filter(club1 -> club1.getName().equals(club.getName())
                        &&
                        club1.getCountry().equals(club.getCountry())).count();

        if(num > 0)
            throw new ObjectExistException("Club already exist");

        return this.clubRepository.save(club);
    }

    @Override
    public List<Club> getAllClubs() {
        return this.clubRepository.findAll();
    }

    @Override
    public Optional<Club> findById(long id) {
        return this.clubRepository.findById(id);
    }

    @Override
    public Club updateClub(long id, String name, int year,String country, String individual, Sport sport) {
        Club club = this.clubRepository.findById(id).orElseThrow(InvalidLeagueException.InvalidClubException::new);

        club.setName(name);
        club.setYear(year);
        club.setCountry(country);
        club.setIndividual(individual);
        club.setSport(sport);

        return this.clubRepository.save(club);
    }

    @Override
    public void deleteClub(long id) {
        this.clubRepository.deleteById(id);
    }
}
