package com.expenseTracker.app.service;

import com.expenseTracker.domain.model.Expense;
import com.expenseTracker.domain.port.in.GetExpenseService;
import com.expenseTracker.domain.port.out.GetExpensePort;
import org.springframework.stereotype.Service;
import java.time.YearMonth;
import java.util.List;

@Service
public class GetExpenseServiceImpl implements GetExpenseService {

    private final GetExpensePort getExpensePort;

    public GetExpenseServiceImpl(GetExpensePort getExpensePort) {
        this.getExpensePort = getExpensePort;
    }

    @Override
    public List<Expense> getAll() {
        return getExpensePort.findAll();
    }

    @Override
    public List<Expense> getByMonth(YearMonth month) {
        return getExpensePort.findByDateRange(month.atDay(1), month.plusMonths(1).atDay(1));
    }
}
