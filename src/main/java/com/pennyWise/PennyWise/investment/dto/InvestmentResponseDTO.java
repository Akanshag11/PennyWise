package com.pennyWise.PennyWise.investment.dto;

import java.time.LocalDate;

public class InvestmentResponseDTO {
    public class InvestmentResponse {
        private Long id;
        private String type;
        private String platform;
        private Double amount;
        private Double returnsPercentage;
        private LocalDate investedDate;
        private String assetName;
    }
}
