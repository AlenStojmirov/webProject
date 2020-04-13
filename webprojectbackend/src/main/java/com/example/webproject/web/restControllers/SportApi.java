package com.example.webproject.web.restControllers;

import com.example.webproject.model.Sport;
import com.example.webproject.service.MapValidationErrorService;
import com.example.webproject.service.SportService;
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
@RequestMapping(path = "/sports",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class SportApi {

    private final SportService sportService;
    private final MapValidationErrorService mapValidationErrorService;

    public SportApi(SportService sportService, MapValidationErrorService mapValidationErrorService) {
        this.sportService = sportService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Sport> getAllSports(){
        return this.sportService.getAllSports();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createSport(@Valid @RequestBody Sport sport, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;
        return this.sportService.save(sport);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Sport> getSport(@PathVariable long id){
        return this.sportService.findById(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Sport updateSport(@PathVariable long id,
                           @RequestBody Sport sport){
        sport.setId(id);
        return this.sportService.save(sport);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSport(@PathVariable long id){
        this.sportService.deleteSport(id);
    }

}
