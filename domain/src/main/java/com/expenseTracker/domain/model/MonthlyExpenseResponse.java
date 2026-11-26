package com.expenseTracker.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

public record MonthlyExpenseResponse(
        YearMonth month,
        BigDecimal total,
        List<Expense> expenses) implements Serializable {}
