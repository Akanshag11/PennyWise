package com.pennyWise.PennyWise.Expense.Repository;

import com.pennyWise.PennyWise.Expense.ExpenseModel.ExpenseEntity;
import com.pennyWise.PennyWise.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {
    //Optional<ExpenseEntity> findByEmail(String email);
    List<ExpenseEntity> findByUser(User user);
    
    void deleteExpenseById(User user, Long id);
}
