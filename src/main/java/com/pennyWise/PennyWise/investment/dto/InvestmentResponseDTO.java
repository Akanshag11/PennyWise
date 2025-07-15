package com.pennyWise.PennyWise.investment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class InvestmentResponseDTO {

        private Long id;
        private String type;
        private Double amount;

        private LocalDate investedDate;

}
