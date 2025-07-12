package com.pennyWise.PennyWise.Expense.Repository;

import com.pennyWise.PennyWise.Expense.ExpenseModel.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

}
