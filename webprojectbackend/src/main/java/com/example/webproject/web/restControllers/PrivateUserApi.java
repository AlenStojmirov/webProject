package com.example.webproject.web.restControllers;

import com.example.webproject.model.PrivateUser;
import com.example.webproject.service.PrivateUserService;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/privateUsers", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class PrivateUserApi {

    private final PrivateUserService privateUserService;

    public PrivateUserApi(PrivateUserService privateUserService) {
        this.privateUserService = privateUserService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PrivateUser> getAllPrivateUsers(){
        return this.privateUserService.getAllPrivateUsers();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PrivateUser createPrivateUser(@RequestBody PrivateUser privateUser){
        return this.privateUserService.save(privateUser);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<PrivateUser> getPrivateUser(@PathVariable long id){
        return this.privateUserService.findById(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PrivateUser updatePrivateUser(@PathVariable long id,
                           @RequestBody PrivateUser privateUser){
        privateUser.setId(id);
        return this.privateUserService.save(privateUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePrivateUser(@PathVariable long id){
        this.privateUserService.deletePrivateUser(id);
    }

}
