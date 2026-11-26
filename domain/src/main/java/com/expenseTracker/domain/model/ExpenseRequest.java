package com.expenseTracker.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseRequest(
        String title,
        BigDecimal amount,
        String category,
        LocalDate date,
        String note) implements Serializable {}
