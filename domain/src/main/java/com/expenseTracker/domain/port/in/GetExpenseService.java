package com.expenseTracker.domain.port.in;

import com.expenseTracker.domain.model.Expense;
import java.util.Optional;

public interface GetExpenseService {
    Optional<Expense> getById(Long id);
}