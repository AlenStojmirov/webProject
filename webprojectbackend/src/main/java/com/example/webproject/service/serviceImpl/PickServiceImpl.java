package com.example.webproject.service.serviceImpl;

import com.example.webproject.model.Pick;
import com.example.webproject.model.User;
import com.example.webproject.model.exception.InvalidPickException;
import com.example.webproject.repository.PickRepository;
import com.example.webproject.repository.UserRepository;
import com.example.webproject.service.PickService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PickServiceImpl implements PickService {

    private final PickRepository pickRepository;
    private final UserRepository userRepository;

    public PickServiceImpl(PickRepository pickRepository, UserRepository userRepository) {
        this.pickRepository = pickRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Pick save(Pick pick , String username) {
        User user = userRepository.findByUsername(username);
        pick.setUser(user);

        return this.pickRepository.save(pick);
    }

    @Override
    public List<Pick> getAllPicks() {
        return this.pickRepository.findAll();
    }

    @Override
    public Optional<Pick> findById(long id) {
        return this.pickRepository.findById(id);
    }

    @Override
    public Pick updatePick(long id, String info, String status_private_public) {
        Pick pick = this.pickRepository.findById(id).orElseThrow(InvalidPickException::new);
        pick.setInfo(info);
        pick.setStatus_private_public(status_private_public);
        return this.pickRepository.save(pick);
    }

    @Override
    public void deletePick(long id) {
        this.pickRepository.deleteById(id);
    }

    @Override
    public List<Pick> getAllPicksWithStatusNull() {
        return pickRepository.getAllPicksWithStatusNull();
    }
}
