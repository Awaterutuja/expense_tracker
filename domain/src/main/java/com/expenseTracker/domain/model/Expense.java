package com.expenseTracker.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Expense(
        Long id,
        String title,
        BigDecimal amount,
        String category,
        LocalDate date,
        String note) {}