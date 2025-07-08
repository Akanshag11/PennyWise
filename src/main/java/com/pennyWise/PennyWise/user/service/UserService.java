package com.pennyWise.PennyWise.user.service;

import com.pennyWise.PennyWise.user.dto.RegisterRequest;
import com.pennyWise.PennyWise.user.model.User;
import com.pennyWise.PennyWise.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;
    private User user;
    
    public void register(RegisterRequest req) {
        if (repo.findByEmail(req.getEmail()).isPresent()) {
            throw new IllegalArgumentException ("Email already in use");
        }
        User user1 = new User();
        user1.setName(req.getName());
        user1.setEmail(req.getEmail());
        user1.setPassword(req.getPassword());
        repo.save(user1);
    }
    
    public void deleteUser(String email) {
        User u = repo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));;
        repo.delete(u);
    }
}
