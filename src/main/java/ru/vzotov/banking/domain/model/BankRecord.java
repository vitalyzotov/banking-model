package ru.vzotov.banking.domain.model;

import ru.vzotov.ddd.shared.Entity;
import ru.vzotov.domain.model.Money;

import java.time.LocalDate;

public interface BankRecord<T extends BankRecord> extends Entity<T> {
    AccountNumber account();
    String description();
    OperationType type();
    Money amount();
    LocalDate recorded();
    String comment();
    BudgetCategoryId category();
    String recordId();
}
