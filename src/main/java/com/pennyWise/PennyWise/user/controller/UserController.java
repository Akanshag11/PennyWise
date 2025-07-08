package com.pennyWise.PennyWise.user.controller;

import com.pennyWise.PennyWise.user.dto.RegisterRequest;
import com.pennyWise.PennyWise.user.repository.UserRepository;
import com.pennyWise.PennyWise.user.service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private UserRepository UserRepository;
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest req) {
        if (UserRepository.findByEmail(req.getEmail()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Email already registered -> " + req.getEmail());
        }
        service.register(req);
        return ResponseEntity.ok("User registered -> " + req.getEmail());
    }
    @DeleteMapping
    public ResponseEntity<String> deleteUser (@RequestBody RegisterRequest req) {
        service.deleteUser(req.getEmail ());
        return ResponseEntity.ok("User deleted -> " + req.getEmail());
    }
    
}
