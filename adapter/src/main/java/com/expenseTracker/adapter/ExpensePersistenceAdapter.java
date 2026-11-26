package com.expenseTracker.adapter;

import com.expenseTracker.domain.model.Expense;
import com.expenseTracker.domain.port.out.AddExpensePort;
import com.expenseTracker.domain.port.out.GetExpensePort;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
public class ExpensePersistenceAdapter implements AddExpensePort, GetExpensePort {

    private static final String INSERT_EXPENSE = """
            INSERT INTO expense_tracker.expenses (id, title, amount, category, expense_date, note)
            VALUES (:id, :title, :amount, :category, :expenseDate, :note)
            """;

    private static final String SELECT_ALL_EXPENSES = """
            SELECT id, title, amount, category, expense_date, note
            FROM expense_tracker.expenses
            ORDER BY expense_date DESC
            """;

    private static final String SELECT_EXPENSES_BY_DATE_RANGE = """
            SELECT id, title, amount, category, expense_date, note
            FROM expense_tracker.expenses
            WHERE expense_date >= :fromDate AND expense_date < :toDate
            ORDER BY expense_date DESC
            """;

    private static final RowMapper<Expense> EXPENSE_ROW_MAPPER = (rs, rowNum) -> new Expense(
            rs.getString("id"),
            rs.getString("title"),
            rs.getBigDecimal("amount"),
            rs.getString("category"),
            rs.getObject("expense_date", java.time.LocalDate.class),
            rs.getString("note"));

    private final JdbcClient jdbcClient;

    public ExpensePersistenceAdapter(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    @Transactional
    public Expense save(Expense expense) {
        jdbcClient.sql(INSERT_EXPENSE)
                .param("id", expense.id())
                .param("title", expense.title())
                .param("amount", expense.amount())
                .param("category", expense.category())
                .param("expenseDate", expense.date())
                .param("note", expense.note())
                .update();

        return expense;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Expense> findAll() {
        return jdbcClient.sql(SELECT_ALL_EXPENSES)
                .query(EXPENSE_ROW_MAPPER)
                .list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Expense> findByDateRange(LocalDate fromInclusive, LocalDate toExclusive) {
        return jdbcClient.sql(SELECT_EXPENSES_BY_DATE_RANGE)
                .param("fromDate", fromInclusive)
                .param("toDate", toExclusive)
                .query(EXPENSE_ROW_MAPPER)
                .list();
    }
}
