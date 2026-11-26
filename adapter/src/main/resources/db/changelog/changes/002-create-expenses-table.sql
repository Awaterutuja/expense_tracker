--liquibase formatted sql

--changeset expenseTracker:002-create-expenses-table
CREATE TABLE expense_tracker.expenses (
    id           VARCHAR(36)    NOT NULL,
    title        VARCHAR(255)   NOT NULL,
    amount       NUMERIC(12,2)  NOT NULL,
    category     VARCHAR(100),
    expense_date DATE           NOT NULL,
    note         VARCHAR(1000),
    CONSTRAINT pk_expenses PRIMARY KEY (id)
);

CREATE INDEX idx_expenses_expense_date ON expense_tracker.expenses (expense_date);
--rollback DROP TABLE expense_tracker.expenses;
