package com.pennyWise.PennyWise.user.controller;

import com.pennyWise.PennyWise.user.dto.RegisterRequest;
import com.pennyWise.PennyWise.user.model.User;
import com.pennyWise.PennyWise.user.repository.UserRepository;
import com.pennyWise.PennyWise.user.service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<ArrayList<User>> getAllUsers() {
        List<User> user=userRepository.findAll();
        return  ResponseEntity.ok(new ArrayList<>(user));
    }
    @GetMapping("id/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable long id) {
        Optional<User> u = userRepository.findById(id);
        if (u.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(u);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest req) {
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
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
