package com.pennyWise.PennyWise.Expense.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ExpenseResponse {
    private Long id;
    private String title;
    private String description;
    private double amount;
    private String category;
    private LocalDate date;
    private String userEmail;
}
