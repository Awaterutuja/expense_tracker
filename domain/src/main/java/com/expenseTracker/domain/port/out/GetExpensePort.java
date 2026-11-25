package com.expenseTracker.domain.port.out;

import com.expenseTracker.domain.model.Expense;
import java.util.Optional;

public interface GetExpensePort {
    Optional<Expense> findById(Long id);
}