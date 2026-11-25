package com.expenseTracker.domain.port.in;

import com.expenseTracker.domain.model.Expense;
import java.math.BigDecimal;
import java.time.LocalDate;

public interface AddExpenseService {

    Expense addExpense(AddExpenseCommand command);

    record AddExpenseCommand(
            String title,
            BigDecimal amount,
            String category,
            LocalDate date,
            String note) {}
}