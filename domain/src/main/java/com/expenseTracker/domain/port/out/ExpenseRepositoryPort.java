package com.expenseTracker.domain.port.out;


import com.expenseTracker.domain.model.Expense;
import java.util.Optional;

public interface ExpenseRepositoryPort {
    Expense save(Expense expense);
    Optional<Expense> findById(String id);
}