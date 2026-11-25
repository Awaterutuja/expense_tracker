package com.expenseTracker.app.service;

import com.expenseTracker.domain.model.Expense;
import com.expenseTracker.domain.port.in.AddExpenseService;
import com.expenseTracker.domain.port.out.AddExpensePort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class AddExpenseServiceImpl implements AddExpenseService {

    private final AddExpensePort addExpensePort;

    public AddExpenseServiceImpl(AddExpensePort addExpensePort) {
        this.addExpensePort = addExpensePort;
    }

    @Override
    public Expense addExpense(AddExpenseCommand command) {
        if (command.amount() == null || command.amount().signum() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        LocalDate date = command.date() != null ? command.date() : LocalDate.now();

        Expense expense = new Expense(null, command.title(), command.amount(),
                command.category(), date, command.note());
        return addExpensePort.save(expense);
    }
}