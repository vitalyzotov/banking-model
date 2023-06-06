package ru.vzotov.banking.domain.model;


import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import ru.vzotov.ddd.shared.AggregateRoot;
import ru.vzotov.ddd.shared.Entity;
import ru.vzotov.domain.model.Money;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Финансовая операция
 */
@AggregateRoot
public class Operation implements Entity<Operation>, BankRecord<Operation> {

    private OperationId operationId;

    private LocalDate date;

    /**
     * Дата авторизации по данной операции.
     * Т.е. для платежей банковской картой это будет дата покупки.
     */
    private LocalDate authorizationDate;

    /**
     * Референс проводки
     */
    private TransactionReference transactionReference;

    /**
     * Категория бюджета
     */
    private BudgetCategoryId category;

    private Money amount;

    private OperationType type;

    private AccountNumber account;

    private String description;

    private String comment;

    public Operation(OperationId operationId, LocalDate date, Money amount, OperationType type, AccountNumber account, String description) {
        this(operationId, null, null, date, amount, type, account, description, null, null);
    }

    public Operation(OperationId operationId, LocalDate date, Money amount, OperationType type, AccountNumber account, String description, BudgetCategoryId category) {
        this(operationId, null, null, date, amount, type, account, description, category, null);
    }

    public Operation(TransactionReference transactionReference,
                     LocalDate authorizationDate,
                     LocalDate date, Money amount, OperationType type, AccountNumber account, String description,
                     BudgetCategoryId category, String comment) {
        this(
                new OperationId(date, type, account, amount, transactionReference),
                transactionReference,
                authorizationDate,
                date,
                amount,
                type,
                account,
                description,
                category,
                comment
        );
    }

    public Operation(OperationId operationId,
                     TransactionReference transactionReference,
                     LocalDate authorizationDate,
                     LocalDate date, Money amount, OperationType type, AccountNumber account, String description,
                     BudgetCategoryId category, String comment) {
        Validate.notNull(operationId);
        Validate.notNull(date);
        Validate.notNull(amount);
        Validate.notNull(type);
        Validate.notNull(account);
        Validate.notEmpty(description);
        Validate.isTrue(description.length() <= 512);

        this.operationId = operationId;
        this.transactionReference = transactionReference;
        this.authorizationDate = authorizationDate;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.type = type;
        this.account = account;
        this.description = description;
        this.comment = comment;
    }

    public OperationId operationId() {
        return operationId;
    }

    public LocalDate date() {
        return date;
    }

    @Override
    public LocalDate recorded() {
        return authorizationDate != null ? authorizationDate() : date();
    }

    @Override
    public String recordId() {
        return operationId().toString();
    }

    @Override
    public BudgetCategoryId category() {
        return category;
    }

    @Override
    public Money amount() {
        return amount;
    }

    @Override
    public OperationType type() {
        return type;
    }

    @Override
    public AccountNumber account() {
        return account;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public String comment() {
        return comment;
    }

    public LocalDate authorizationDate() {
        return authorizationDate;
    }

    public TransactionReference transactionReference() {
        return transactionReference;
    }

    public void assignTransaction(TransactionReference transactionReference) {
        Validate.notNull(transactionReference);
        this.transactionReference = transactionReference;
    }

    public void specifyAuthorization(LocalDate authorizationDate) {
        Validate.notNull(authorizationDate);
        this.authorizationDate = authorizationDate;
    }

    public void assignCategory(BudgetCategoryId category) {
        this.category = category;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean sameIdentityAs(Operation other) {
        return other != null && new EqualsBuilder()
                .append(operationId, other.operationId)
                .isEquals();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Operation operation = (Operation) o;
        return sameIdentityAs(operation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationId);
    }

    @Override
    public String toString() {
        return "Operation{" + operationId + '}';
    }

    protected Operation() {
        // for Hibernate
    }
}
