package com.pennyWise.PennyWise.Expense.Controller;

import com.pennyWise.PennyWise.Expense.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
        if(!bearerToken.startsWith ("Bearer ")) {
            return ResponseEntity.badRequest ().build ();
        }
        String token = bearerToken.substring (7).trim ();
        expenseService.clearAllExpenses(token);
        return ResponseEntity.ok("All Expense deleted");
    }
    
    
    @PutMapping("/updateExpense/id/{id}")
    public ResponseEntity<String> updateExpense(@RequestHeader("Authorization") String bearerToken, @PathVariable Long id,@RequestBody ExpenseRequest expenseRequest) {
        if(!bearerToken.startsWith ("Bearer ")) {
            return ResponseEntity.badRequest ().build ();
        }
        String token = bearerToken.substring (7).trim ();
        expenseService.updateExpense(token, id,expenseRequest);
        return ResponseEntity.ok("Expense updated");
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ExpenseResponse>> filterExpenses(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        String token = bearerToken.replace("Bearer ", "");
        List<ExpenseResponse> results = expenseService.filterExpenses(
                token, category, minAmount, maxAmount, startDate, endDate);
        if(results.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(results);
    }

//    @GetMapping("/sort")
//    public ResponseEntity<List<ExpenseResponse>> SortExpense(@RequestHeader("Authorization") String bearerToken,)

}
