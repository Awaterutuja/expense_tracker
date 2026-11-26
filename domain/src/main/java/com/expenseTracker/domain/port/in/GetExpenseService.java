package com.expenseTracker.domain.port.in;

import com.expenseTracker.domain.model.Expense;
import java.time.YearMonth;
import java.util.List;

public interface GetExpenseService {
    List<Expense> getAll();

    List<Expense> getByMonth(YearMonth month);
}
