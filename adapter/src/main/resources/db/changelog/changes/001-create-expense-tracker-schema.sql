--liquibase formatted sql

--changeset expenseTracker:001-create-expense-tracker-schema
CREATE SCHEMA IF NOT EXISTS expense_tracker;
--rollback DROP SCHEMA expense_tracker;
