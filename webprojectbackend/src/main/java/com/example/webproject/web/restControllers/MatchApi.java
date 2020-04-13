package com.example.webproject.web.restControllers;

import com.example.webproject.model.SportMatch;
import com.example.webproject.service.MapValidationErrorService;
import com.example.webproject.service.MatchService;
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
@RequestMapping(path = "/matches",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class MatchApi {

    private final MatchService matchService;
    private final MapValidationErrorService mapValidationErrorService;

    public MatchApi(MatchService matchService, MapValidationErrorService mapValidationErrorService) {
        this.matchService = matchService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SportMatch> getAllMatches(){
        return this.matchService.getAllMatches();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createMatch(@Valid  @RequestBody SportMatch match, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;
        return this.matchService.save(match);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<SportMatch> getMatch(@PathVariable long id){
        return this.matchService.findById(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SportMatch updateMatch(@PathVariable long id,
                             @RequestBody SportMatch match){
        match.setId(id);
        return this.matchService.save(match);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMatch(@PathVariable long id){
        this.matchService.deleteMatch(id);
    }
}