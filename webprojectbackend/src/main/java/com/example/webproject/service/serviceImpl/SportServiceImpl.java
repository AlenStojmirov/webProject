package com.example.webproject.service.serviceImpl;

import com.example.webproject.model.Sport;
import com.example.webproject.model.exception.InvalidSportException;
import com.example.webproject.model.exception.ObjectExistException;
import com.example.webproject.repository.SportRepository;
import com.example.webproject.service.SportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SportServiceImpl implements SportService {

    private final SportRepository sportRepository;

    public SportServiceImpl(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    @Override
    public Sport save(Sport sport) {
        try {
            return this.sportRepository.save(sport);
        } catch (Exception e) {
            throw new ObjectExistException("This sport already exist");
        }
    }

    @Override
    public List<Sport> getAllSports() {
        return this.sportRepository.findAll();
    }

    @Override
    public Optional<Sport> findById(long id) {
        return this.sportRepository.findById(id);
    }

    @Override
    public Sport updateSport(long id, String name) {
        Sport sport = this.sportRepository.findById(id).orElseThrow(InvalidSportException::new);
        sport.setName(name);

        return this.sportRepository.save(sport);
    }

    @Override
    public void deleteSport(long id) {
        this.sportRepository.deleteById(id);
    }
}
