package com.expenseTracker.app.controller;

import com.expenseTracker.domain.model.Expense;
import com.expenseTracker.domain.model.MonthlyExpenseResponse;
import com.expenseTracker.domain.port.in.GetExpenseService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class MonthlyExpenseController {

    private final GetExpenseService getExpenseService;

    public MonthlyExpenseController(GetExpenseService getExpenseService) {
        this.getExpenseService = getExpenseService;
    }

    @GetMapping("/monthly")
    public ResponseEntity<MonthlyExpenseResponse> getMonthlyExpenses(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
        YearMonth target = month != null ? month : YearMonth.now();
        List<Expense> expenses = getExpenseService.getByMonth(target);
        BigDecimal total = expenses.stream()
                .map(Expense::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return ResponseEntity.ok(new MonthlyExpenseResponse(target, total, expenses));
    }
}
