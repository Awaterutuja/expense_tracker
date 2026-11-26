package com.expenseTracker.app.controller;


import com.expenseTracker.domain.model.Expense;
import com.expenseTracker.domain.model.ExpenseRequest;
import com.expenseTracker.domain.model.ExpenseResponse;
import com.expenseTracker.domain.port.in.AddExpenseService;
import com.expenseTracker.domain.port.in.AddExpenseService.AddExpenseCommand;
import com.expenseTracker.domain.port.in.GetExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final GetExpenseService getExpenseService;
    private final AddExpenseService addExpenseService;

    public ExpenseController(GetExpenseService getExpenseService, AddExpenseService addExpenseService){
        this.addExpenseService = addExpenseService;
        this.getExpenseService= getExpenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> addExpense(@RequestBody ExpenseRequest request) {
        AddExpenseCommand command = new AddExpenseCommand(request.title(), request.amount(),
                request.category(), request.date(), request.note());
        Expense saved = addExpenseService.addExpense(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ExpenseResponse(List.of(saved)));
    }

    @GetMapping
    public ResponseEntity<ExpenseResponse> getAllExpenses() {
        return ResponseEntity.ok(new ExpenseResponse(getExpenseService.getAll()));
    }
}
