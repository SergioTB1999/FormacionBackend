package com.Bosonit.block7crudvalidation2.auth;

import com.Bosonit.block7crudvalidation2.domain.Person;
import com.Bosonit.block7crudvalidation2.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    PersonRepository personRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest, @RequestParam boolean isAdmin){
        return ResponseEntity.ok(authService.register(registerRequest, isAdmin));
    }
}
