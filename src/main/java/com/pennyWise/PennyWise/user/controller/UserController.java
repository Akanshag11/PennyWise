package com.pennyWise.PennyWise.user.controller;

import com.pennyWise.PennyWise.jwt.JwtService;
import com.pennyWise.PennyWise.user.dto.AuthResponse;
import com.pennyWise.PennyWise.user.dto.LoginRequest;
import com.pennyWise.PennyWise.user.dto.RegisterRequest;
import com.pennyWise.PennyWise.user.model.BlacklistedToken;
import com.pennyWise.PennyWise.user.model.User;
import com.pennyWise.PennyWise.user.repository.BlacklistedTokenRepository;
import com.pennyWise.PennyWise.user.repository.UserRepository;
import com.pennyWise.PennyWise.user.service.UserService;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlacklistedTokenRepository blacklistRepo;

    @Autowired
    private JwtService jwtService;

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
        return ResponseEntity.ok().body(service.login(logreq));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody String refreshToken) {
        return service.refreshTok(refreshToken);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @RequestHeader("Authorization") String bearerToken) {

        // ✅ Strip "Bearer " prefix
        if (!bearerToken.startsWith("Bearer ")) {
            System.out.println(bearerToken);
            return ResponseEntity.badRequest().body("Invalid Authorization header format");
        }

        String token = bearerToken.substring(7).trim(); // "Bearer " is 7 chars long
        return service.logout(token);
    }
    
    @GetMapping("/profile")
    public ResponseEntity<AuthResponse> getProfile(@RequestHeader("Authorization") String bearerToken) {
        if(!bearerToken.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().build();
        }
        String token = bearerToken.substring(7).trim();
        return ResponseEntity.ok().body(service.getProfile(token));
    }
    
    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestHeader("Authorization") String bearerToken) {
        if(!bearerToken.startsWith("Bearer ")) {
            return ResponseEntity.badRequest ().build ();
        }
        String token = bearerToken.substring(7).trim();
        service.deleteUser(token);
        return ResponseEntity.ok("Account deleted successfully");
    }


//    TODO: CHANGE PASSWORD, RESET PASSWORD ENDPOINTS, if the person is already logged in and tries again tell them that they are already logged in .ie bearer token is active

    
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
