package org.usvm.spring.auth.benchmarks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.usvm.spring.auth.benchmarks.models.RegisterUser;
import org.usvm.spring.auth.benchmarks.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private final AuthenticationManager authenticationManager;

    public UserController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public record LoginRequest(String username, String password) {
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterUser registerUser) {
        userService.createNewUser(registerUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/username")
    public ResponseEntity<String> username(Authentication authentication) {
        var name = authentication.getName();
        return new ResponseEntity<>(name, HttpStatus.OK);
    }
}
