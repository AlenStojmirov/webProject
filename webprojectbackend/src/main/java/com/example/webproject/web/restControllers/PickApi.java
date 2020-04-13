package com.example.webproject.web.restControllers;

import com.example.webproject.model.Pick;
import com.example.webproject.service.MapValidationErrorService;
import com.example.webproject.service.PickService;
import com.example.webproject.service.serviceImpl.CustomUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/picks", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class PickApi {

    private final PickService pickService;
    private final MapValidationErrorService mapValidationErrorService;
    private final CustomUserDetailsService customUserDetailsService;

    public PickApi(PickService pickService, MapValidationErrorService mapValidationErrorService, CustomUserDetailsService customUserDetailsService) {
        this.pickService = pickService;
        this.mapValidationErrorService = mapValidationErrorService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pick> getAllPicks(){
        return this.pickService.getAllPicks();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createPick(@Valid @RequestBody Pick pick, BindingResult result, Principal principal){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        return pickService.save(pick,principal.getName());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Pick> getPick(@PathVariable long id){
        return this.pickService.findById(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Pick updatePick(@PathVariable long id,
                               @RequestBody Pick pick){
        pick.setId(id);
        return this.pickService.save(pick,pick.getUser().getUsername());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLeague(@PathVariable long id){
        this.pickService.deletePick(id);
    }
}