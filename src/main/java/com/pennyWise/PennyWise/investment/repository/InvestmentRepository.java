package com.pennyWise.PennyWise.investment.repository;

import com.pennyWise.PennyWise.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<User, Long> {

}
