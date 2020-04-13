package com.example.webproject.web.restControllers;

import com.example.webproject.model.Role;
import com.example.webproject.service.MapValidationErrorService;
import com.example.webproject.service.RoleService;
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
@RequestMapping(path = "/roles",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class RoleApi {

    private final RoleService roleService;
    private final MapValidationErrorService mapValidationErrorService;

    public RoleApi(RoleService roleService, MapValidationErrorService mapValidationErrorService) {
        this.roleService = roleService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Role> getAllRoles(){
        return this.roleService.getAllRoles();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createRole(@Valid @RequestBody Role role, BindingResult result){
        ResponseEntity<?> errorMap = this.mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;
        return this.roleService.save(role);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Role> getRole(@PathVariable long id){
        return this.roleService.findById(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Role updateRole(@PathVariable long id,
                           @RequestBody Role role){
        role.setId(id);
        return this.roleService.save(role);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRole(@PathVariable long id){
        this.roleService.deleteRole(id);
    }

}
