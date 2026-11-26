package com.expenseTracker.domain.model;


import java.io.Serializable;
import java.util.List;

public record ExpenseResponse(List<Expense> expenses) implements Serializable {}
