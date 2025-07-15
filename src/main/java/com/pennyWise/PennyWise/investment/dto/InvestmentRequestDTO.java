package com.pennyWise.PennyWise.investment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvestmentRequestDTO {
        @NotBlank
        private String type;
        
        @Positive
        private Double amount;
        
        private LocalDate investedDate;
    }

