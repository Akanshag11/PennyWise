package com.pennyWise.PennyWise.user.controller;

import com.pennyWise.PennyWise.user.dto.AuthResponse;
import com.pennyWise.PennyWise.user.dto.LoginRequest;
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
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest logreq) {
        return ResponseEntity.ok().body(service.login (logreq));
    }


   /* @GetMapping
    public ResponseEntity<List<AuthResponse>> getAllUsers() {
        List<User> users = userRepository.findAll();

        return ResponseEntity.ok(service.getUser(users));
    }

    @GetMapping("id/{id}")
    public ResponseEntity<List<AuthResponse>> getUserById(@PathVariable long id) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Convert single user to a list of AuthResponse
        List<AuthResponse> response = service.getUser(List.of(userOpt.get()));
        return ResponseEntity.ok(response);
    }*/


    
   /* @DeleteMapping
    public ResponseEntity<String> deleteUser (@RequestBody RegisterRequest req) {
        service.deleteUser(req.getEmail ());
        return ResponseEntity.ok("User deleted -> " + req.getEmail());
    }
*/

    /*@PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest logreq)
    {

    }*/

    
}
