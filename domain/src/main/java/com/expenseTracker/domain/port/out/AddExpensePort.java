package com.expenseTracker.domain.port.out;

import com.expenseTracker.domain.model.Expense;

public interface AddExpensePort {
    Expense save(Expense expense);
}