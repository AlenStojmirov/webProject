package com.example.webproject.web.restControllers;

import com.example.webproject.model.Club;
import com.example.webproject.service.ClubService;
import java.util.List;
import java.util.Optional;

import com.example.webproject.service.MapValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "clubs", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class ClubApi {

    private final ClubService clubService;
    private final MapValidationErrorService mapValidationErrorService;

    public ClubApi(ClubService clubService, MapValidationErrorService mapValidationErrorService) {
        this.clubService = clubService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Club> getAllClubs(){
        return this.clubService.getAllClubs();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createClub(@Valid @RequestBody Club club, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;
        return this.clubService.save(club);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Club> getClub(@PathVariable long id){
        return this.clubService.findById(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Club updateClub(@PathVariable long id,
                                   @RequestBody Club club){
        club.setId(id);
        return this.clubService.save(club);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClub(@PathVariable long id){
        this.clubService.deleteClub(id);
    }

}
