package com.expenseTracker.domain.port.out;

import com.expenseTracker.domain.model.Expense;
import java.time.LocalDate;
import java.util.List;

public interface GetExpensePort {
    List<Expense> findAll();

    /** Returns expenses with a date in the half-open range [fromInclusive, toExclusive). */
    List<Expense> findByDateRange(LocalDate fromInclusive, LocalDate toExclusive);
}
