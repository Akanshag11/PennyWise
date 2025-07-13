package com.pennyWise.PennyWise.Expense.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseRequest {
    @NotBlank
    private String title;

    private String description;

    @Positive
    private double amount;

    @NotBlank
    private String category;

    @NotNull
    private LocalDate date;
}
