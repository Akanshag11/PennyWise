package com.pennyWise.PennyWise.investment.inv_model;

import com.pennyWise.PennyWise.user.model.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class InvestmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String type; // e.g., Stock, SIP, Crypto
    private String platform; // Zerodha, Groww, Coin, etc.
    
    private Double amount;
    
    private LocalDate investedDate;
    
    private String assetName; // e.g., Infosys, Bitcoin
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
