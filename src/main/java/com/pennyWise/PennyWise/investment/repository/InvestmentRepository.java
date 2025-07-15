package com.pennyWise.PennyWise.investment.repository;

import com.pennyWise.PennyWise.investment.inv_model.InvestmentEntity;
import com.pennyWise.PennyWise.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvestmentRepository extends JpaRepository<InvestmentEntity, Long> {
      List<InvestmentEntity> findByUser(User user);
}
