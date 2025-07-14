package com.pennyWise.PennyWise.Expense.Repository;

import com.pennyWise.PennyWise.Expense.ExpenseModel.ExpenseEntity;
import com.pennyWise.PennyWise.user.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {
    //Optional<ExpenseEntity> findByEmail(String email);
    List<ExpenseEntity> findByUser(User user);

    Optional<ExpenseEntity> findById(Long id);
    @Query("SELECT e FROM ExpenseEntity e WHERE e.user = :user " +
            "AND (:category IS NULL OR e.category = :category) " +
            "AND (:minAmount IS NULL OR e.amount >= :minAmount) " +
            "AND (:maxAmount IS NULL OR e.amount <= :maxAmount) " +
            "AND (:startDate IS NULL OR e.date >= :startDate) " +
            "AND (:endDate IS NULL OR e.date <= :endDate)")
    List<ExpenseEntity> filterExpenses(
            @Param("user") User user,
            @Param("category") String category,
            @Param("minAmount") Double minAmount,
            @Param("maxAmount") Double maxAmount,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
    void deleteAll(Iterable<? extends ExpenseEntity> entities);
   //another way of writing the above method
   /*@Modifying
    @Transactional
    @Query("DELETE FROM ExpenseEntity e WHERE e.user = :user")
    void deleteAllExpensesByUser(@Param("user") User user);*/
    @Query("Select e FROM ExpenseEntity e WHERE e.user=:user")
    List<ExpenseEntity> findByUser(@Param("user") User user, Sort sort);
}
