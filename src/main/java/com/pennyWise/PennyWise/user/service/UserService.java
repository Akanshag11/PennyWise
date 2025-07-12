package com.pennyWise.PennyWise.user.service;

import com.pennyWise.PennyWise.jwt.JwtService;
import com.pennyWise.PennyWise.user.dto.AuthResponse;
import com.pennyWise.PennyWise.user.dto.LoginRequest;
import com.pennyWise.PennyWise.user.dto.RegisterRequest;
import com.pennyWise.PennyWise.user.model.BlacklistedToken;
import com.pennyWise.PennyWise.user.model.User;
import com.pennyWise.PennyWise.user.repository.BlacklistedTokenRepository;
import com.pennyWise.PennyWise.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    @Autowired
    private BlacklistedTokenRepository blacklistedToken;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    
    public void register(RegisterRequest req) {
        if (repo.findByEmail(req.getEmail()).isPresent()) {
            throw new IllegalArgumentException ("Email already in use");
        }
        User user1 = new User();
        user1.setName(req.getName());
        user1.setEmail(req.getEmail());
        user1.setPassword(passwordEncoder.encode(req.getPassword()));
        repo.save(user1);
    }
    public AuthResponse login(LoginRequest logreq){
        User u = repo.findByEmail(logreq.email()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if(!passwordEncoder.matches(logreq.password(), u.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        String token = jwtService.generateToken(u.getEmail());
        String refreshToken = jwtService.generateRefreshToken(u.getEmail());

        u.setRefreshToken(refreshToken);
        u.setRefreshTokenExpiry(jwtService.getExpiration(refreshToken));
        repo.save(u);
        return new AuthResponse (" Logged in Successfully 🎉", u.getName(),u.getEmail (),token,refreshToken);
    }

    public ResponseEntity<AuthResponse> refreshTok(String refreshToken){
        if (!jwtService.isTokenValid(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = jwtService.extractEmail(refreshToken);
        User user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!refreshToken.equals(user.getRefreshToken()) || new Date().after(user.getRefreshTokenExpiry())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String newAccessToken = jwtService.generateToken(email);
        return ResponseEntity.ok(new AuthResponse("Re-login successful", user.getName(), user.getEmail(), newAccessToken, refreshToken));
    }


    public ResponseEntity<String> logout(String bearerToken) {

        String token = bearerToken.replace("Bearer ", "");
        String jti = jwtService.extractTokenId(token);
        Date expiry = jwtService.getExpiration(token);

        // Blacklist the token
        blacklistedToken.save(new BlacklistedToken(jti, expiry));

        // ✅ Extract email from token instead of receiving from body
        String email = jwtService.extractEmail(token);
        User user = repo.findByEmail(email).orElse(null);
        if (user == null) {
            System.out.println("Email from token: " + email);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        user.setRefreshToken(null);
        user.setRefreshTokenExpiry(null);
        repo.save(user);

        return ResponseEntity.ok("User logged out: access token blacklisted, refresh token cleared.");
    }
    
    public AuthResponse getProfile(String bearerToken) {
        String token = bearerToken.replace("Bearer ",  "");
        String email = jwtService.extractEmail (bearerToken);
        User user = repo.findByEmail(email).orElse(null);
        return new AuthResponse ("Here is your profile ", user.getName (),user.getEmail (),token, user.getRefreshToken ());
    }
    
    public void deleteUser(String bearerToken) {
        String token = bearerToken.replace("Bearer ",  "");
        String email = jwtService.extractEmail (bearerToken);
        User user = repo.findByEmail(email).orElse(null);
        String jti = jwtService.extractTokenId(token);
        Date expiry = jwtService.getExpiration(token);
        blacklistedToken.save (new BlacklistedToken (jti, expiry));
        repo.delete(user);
    }




   /* public void deleteUser(String email) {
        User u = repo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));;
        repo.delete(u);
    }

    public List<AuthResponse> getUser(List<User> users)
    {
        List<AuthResponse> response = users.stream()
                .map(user -> new AuthResponse(user.getName(), user.getEmail()))
                .toList();

        return response;

    }
*/


}
