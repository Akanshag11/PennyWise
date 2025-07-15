package com.pennyWise.PennyWise.investment.inv_model;

import com.pennyWise.PennyWise.user.model.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class InvestmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String type; // e.g., Stock, SIP, Crypto
    
    private Double amount;
    
    private LocalDate investedDate;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
