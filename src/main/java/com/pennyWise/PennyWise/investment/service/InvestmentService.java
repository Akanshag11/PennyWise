package com.pennyWise.PennyWise.investment.service;

import com.pennyWise.PennyWise.investment.dto.InvestmentRequestDTO;
import com.pennyWise.PennyWise.investment.dto.InvestmentResponseDTO;
import com.pennyWise.PennyWise.investment.inv_model.InvestmentEntity;
import com.pennyWise.PennyWise.investment.repository.InvestmentRepository;
import com.pennyWise.PennyWise.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.pennyWise.PennyWise.jwt.JwtService;

import java.util.List;


@Service
public class InvestmentService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private InvestmentRepository repo;

    public void addInvestment(String token, InvestmentRequestDTO req) {
        User user=jwtService.extractUser(token);

        InvestmentEntity env=new InvestmentEntity();
        env.setType(req.getType());
        env.setAmount(req.getAmount());
        env.setUser(user);
        env.setInvestedDate(req.getInvestedDate());

        repo.save(env);


    }

}
