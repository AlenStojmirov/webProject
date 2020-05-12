package com.example.webproject.web.restControllers;

import com.example.webproject.model.User;
import com.example.webproject.payload.JWTLoginSuccessResponse;
import com.example.webproject.payload.LoginRequest;
import com.example.webproject.security.JwtTokenProvider;
import com.example.webproject.service.MapValidationErrorService;
import com.example.webproject.service.UserService;
import com.example.webproject.validator.UserValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.example.webproject.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/users",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class UserApi {

    private final UserService userService;
    private final MapValidationErrorService mapValidationErrorService;
    private final UserValidator userValidator;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public UserApi(UserService userService, MapValidationErrorService mapValidationErrorService, UserValidator userValidator, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.mapValidationErrorService = mapValidationErrorService;
        this.userValidator = userValidator;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX +  jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers(){
        return this.userService.getAllUsers();
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createUser(@Valid @RequestBody User user, BindingResult result){
        userValidator.validate(user,result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        return this.userService.save(user);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<User> getUser(@PathVariable long id){
        return this.userService.findById(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User updateUser(@PathVariable long id,
                                     @RequestBody User user){
        user.setId(id);
        return this.userService.save(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable long id){
        this.userService.deleteUser(id);
    }

    @GetMapping("/theBest5TipsterByProfit")
    public List<User> theBest5TipsterByProfit(){
        return this.userService.theBest5TipsterByProfit();
    }

    @GetMapping("/theBest5TipsterByProfit")
    public List<User> theBest5TipstersByWinRatio(){
        return this.userService.theBest5TipstersByWinRatio();
    }
    @GetMapping("/theBest5TipsterByProfit")
    public List<User> theBest5TipsterByWinRatioInLast30Days(){
        return this.userService.theBest5TipsterByWinRatioInLast30Days();
    }
    @GetMapping("/theBest5TipsterByProfit")
    public List<User> theBest5TipsterByProfitInLast30Days(){
        return this.userService.theBest5TipsterByProfitInLast30Days();
    }

}
