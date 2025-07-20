package com.pennyWise.PennyWise.user;

import com.pennyWise.PennyWise.jwt.JwtService;
import com.pennyWise.PennyWise.user.dto.LoginRequest;
import com.pennyWise.PennyWise.user.dto.RegisterRequest;
import com.pennyWise.PennyWise.user.model.User;
import com.pennyWise.PennyWise.user.repository.BlacklistedTokenRepository;
import com.pennyWise.PennyWise.user.repository.UserRepository;
import com.pennyWise.PennyWise.user.service.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.Authenticator;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith (MockitoExtension.class)
public class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private BlacklistedTokenRepository blacklistedTokenRepository;
    
    @InjectMocks
    private UserService userService;
    private User user;
    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;
    
    @BeforeAll
    static void setupAll() {
        System.out.println("Starting tests for UserService...");
        // This is where you would set up resources needed by all tests.
    }
    
    @BeforeEach
    void setup(){
        user = new User ();
        user.setId (1L);
        user.setEmail ("email@example.com");
        user.setName ("Test user");
        user.setPassword ("hashedPassword");
        
        registerRequest = new RegisterRequest ("Test user","email@example.com","password123");
        loginRequest =new LoginRequest ("email@example.com","password123");
    }
    @Test
    void testUserRegistration_whenUEmailIsNew_shouldSaveUser() {
        when(userRepository.findByEmail (anyString ())).thenReturn (Optional.empty ());
        when (passwordEncoder.encode (anyString ())).thenReturn ("hashedPassword");
        when (userRepository.save (any (User.class))).thenReturn (user);
        //any() and anyString() are arguement matchers
        userService.register(registerRequest);
        
        verify (userRepository,times (1)).save (any (User.class));
        verify (passwordEncoder,times (1)).encode ("password123");
    
    }
    @Test
    void testUserRegistration_whenUEmailIsNotNew_shouldThrowException() {
        // Arrange
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        
        // Act & Assert
        // This will check that the code inside the lambda throws the expected exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.register(registerRequest);
        });
            
        // You can even check the exception message
        assertEquals("Email already in use", exception.getMessage());
            
        // And finally, verify that save was never called
        verify(userRepository, never()).save(any(User.class));
    }
    @Test
    void loginUser_withValidaCredentials_shouldReturnJWTToken() {
        String expectedToken = "dummy.jwt.token";
        
    }
    @Test
    void loginUser_withInvalidCredientials_shouldThrowException() {
    
    }
    @Test
    void logoutUser_shouldBlackListToken() {
    
    }
    @Test
    void deleteUser_whenUserExists_shouldDeleteUser(){
    
    }
    @Test
    void deleteUser_whenUserDOesNotExist_shouldThrowException() {
    }
    
    @AfterAll
    static void tearDownAll() {
        System.out.println("All UserService tests have been completed.");
    }
    
}

