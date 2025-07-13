package com.pennyWise.PennyWise.Expense.Service;

import com.pennyWise.PennyWise.Expense.ExpenseModel.ExpenseEntity;
import com.pennyWise.PennyWise.Expense.Repository.ExpenseRepository;
import com.pennyWise.PennyWise.jwt.JwtService;
import com.pennyWise.PennyWise.user.model.User;
import com.pennyWise.PennyWise.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pennyWise.PennyWise.Expense.dto.ExpenseResponse;
import com.pennyWise.PennyWise.Expense.dto.ExpenseRequest;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;
    
    public List<ExpenseResponse> getAllExpenses(String token) {
        String email = jwtService.extractEmail (token);
        User user = userRepository.findByEmail(email).orElse(null);
        List<ExpenseEntity> expenses = expenseRepository.findByUser (user);
        return expenses.stream ().map (expenseEntity1 -> new ExpenseResponse (
                expenseEntity1.getId (),
                expenseEntity1.getTitle (),
                expenseEntity1.getDescription (),
                expenseEntity1.getAmount (),
                expenseEntity1.getCategory (),
                expenseEntity1.getDate ())).toList (); // else we can use some utility class to map the expenseEntity to the expenseResponse
    }
    
    public void addExpense(String token, ExpenseRequest expenseRequest) {
        String email = jwtService.extractEmail (token);
        User user = userRepository.findByEmail (email).orElse (null);
        ExpenseEntity expenseEntity = new ExpenseEntity ();
        expenseEntity.setTitle (expenseRequest.getTitle ());
        expenseEntity.setDescription (expenseRequest.getDescription ());
        expenseEntity.setAmount (expenseRequest.getAmount ());
        expenseEntity.setCategory (expenseRequest.getCategory ());
        expenseEntity.setDate (expenseRequest.getDate ());
        expenseEntity.setUser (user);
        expenseRepository.save (expenseEntity);
    }
    
    public void deleteExpense(String token, Long id) {

        User u=jwtService.extractUser(token);
        ExpenseEntity expenseEntity=expenseRepository.findById(id).orElse(null);
        expenseRepository.delete(expenseEntity);

    }
    
}
