package com.pennyWise.PennyWise.Expense.Controller;

import com.pennyWise.PennyWise.Expense.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pennyWise.PennyWise.Expense.dto.ExpenseResponse;
import com.pennyWise.PennyWise.Expense.dto.ExpenseRequest;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;
    
    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getAllExpenses(@RequestHeader("Authorization") String bearerToken) {
        if(!bearerToken.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().build();
        }
        String token = bearerToken.substring(7).trim();
        List<ExpenseResponse> expenses = expenseService.getAllExpenses(token);
        return ResponseEntity.ok(expenses);
    }
    
    
    @PostMapping("/addExpense")
    public ResponseEntity<String> addExpense(@RequestHeader("Authorization") String bearerToken,@RequestBody ExpenseRequest expenseRequest) {
        if(!bearerToken.startsWith ("Bearer ")) {
            return ResponseEntity.badRequest ().build ();
        }
        String token = bearerToken.substring (7).trim ();
        expenseService.addExpense(token, expenseRequest);
        return ResponseEntity.ok("Expense added successfully");
    }
    
    @DeleteMapping("id/{id}")
    public ResponseEntity<String> deleteExpense(@RequestHeader("Authorization") String bearerToken,@PathVariable Long id) {
        if(!bearerToken.startsWith ("Bearer ")) {
            return ResponseEntity.badRequest ().build ();
        }
        String token = bearerToken.substring (7).trim ();
        expenseService.deleteExpense(token, id);
        return ResponseEntity.ok ("Expense has been successfully deleted!");
    }

    @DeleteMapping
    public ResponseEntity<String> clearAllExpenses(@RequestHeader("Authorization") String bearerToken) {
        return ResponseEntity.ok("Expense deleted");
    }
    
    
    @PutMapping("/updateExpense")
    public ResponseEntity<String> updateExpense(@RequestHeader("Authorization") String bearerToken,@RequestBody ExpenseRequest expenseRequestDTO) {
        return ResponseEntity.ok("Expense updated");
    }
}
