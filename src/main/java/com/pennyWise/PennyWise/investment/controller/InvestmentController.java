package com.pennyWise.PennyWise.investment.controller;

import com.pennyWise.PennyWise.investment.dto.InvestmentRequestDTO;
import com.pennyWise.PennyWise.investment.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/investment")
public class InvestmentController {

    @Autowired
    private InvestmentService service;


    @PostMapping("/addInv")
    public ResponseEntity<String> addInvestment(@RequestHeader("Authorization") String bearerToken, @RequestBody InvestmentRequestDTO req)
    {
        if(!bearerToken.startsWith("Bearer "))
            return ResponseEntity.badRequest().build();
        String token=bearerToken.substring(7).trim();
        service.addInvestment(token,req);
        return ResponseEntity.ok("Investment added ");
    }
}
