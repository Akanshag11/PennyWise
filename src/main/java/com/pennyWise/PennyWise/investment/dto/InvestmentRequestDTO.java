package com.pennyWise.PennyWise.investment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class InvestmentRequestDTO {
    public class InvestmentRequest {
        @NotBlank
        private String type;
        
        private String platform;
        
        @Positive
        private Double amount;
        
        private LocalDate investedDate;
        
        private String assetName;
    }
    
}
