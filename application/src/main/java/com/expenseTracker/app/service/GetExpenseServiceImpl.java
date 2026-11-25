package com.expenseTracker.app.service;

import com.expenseTracker.domain.model.Expense;
import com.expenseTracker.domain.port.in.GetExpenseService;
import com.expenseTracker.domain.port.out.GetExpensePort;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class GetExpenseServiceImpl implements GetExpenseService {

    private final GetExpensePort getExpensePort;

    public GetExpenseServiceImpl(GetExpensePort getExpensePort) {
        this.getExpensePort = getExpensePort;
    }

    @Override
    public Optional<Expense> getById(Long id) {
        return getExpensePort.findById(id);
    }
}