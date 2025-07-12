package com.pennyWise.PennyWise.Expense.ExpenseModel;

import com.pennyWise.PennyWise.user.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
@Entity
public class ExpenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String title;
    private String description;
    private double amount;
    private String category;
    private Date date;
    private Time createdAt;
    private Time updatedAt;
}
