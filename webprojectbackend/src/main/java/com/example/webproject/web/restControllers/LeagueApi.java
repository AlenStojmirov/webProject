package com.example.webproject.web.restControllers;

import com.example.webproject.model.League;
import com.example.webproject.service.LeagueService;
import com.example.webproject.service.MapValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "leagues", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class LeagueApi {

    private final LeagueService leagueService;
    private final MapValidationErrorService mapValidationErrorService;

    public LeagueApi(LeagueService leagueService, MapValidationErrorService mapValidationErrorService) {
        this.leagueService = leagueService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<League> getAllLeagues(){
        return this.leagueService.getAllLeagues();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createLeague(@Valid @RequestBody League league, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;
        return this.leagueService.save(league);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<League> getLeague(@PathVariable long id){
        return this.leagueService.findById(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public League updateLeague(@PathVariable long id,
                           @RequestBody League league){
        league.setId(id);
        return this.leagueService.save(league);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLeague(@PathVariable long id){
        this.leagueService.deleteLeague(id);
    }

}
