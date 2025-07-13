package com.pennyWise.PennyWise.Expense.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ExpenseResponse {
    private Long id;
    private String title;
    private String description;
    private Double amount;
    private String category;
    private LocalDate date;
}
