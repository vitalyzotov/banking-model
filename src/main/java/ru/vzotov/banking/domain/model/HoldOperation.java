package ru.vzotov.banking.domain.model;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import ru.vzotov.ddd.shared.AggregateRoot;
import ru.vzotov.ddd.shared.Entity;
import ru.vzotov.domain.model.Money;

import java.time.LocalDate;
import java.util.Objects;

@AggregateRoot
public class HoldOperation implements Entity<HoldOperation>, BankRecord<HoldOperation> {

    /**
     * Уникальный идентификатор
     */
    private HoldId holdId;

    /**
     * Дата блокирования средств на счете
     */
    private LocalDate date;

    /**
     * Сумма блокировки
     */
    private Money amount;

    /**
     * Тип операции
     */
    private OperationType type;

    /**
     * Счет
     */
    private AccountNumber account;

    /**
     * Описание операции
     */
    private String description;

    /**
     * Комментарий пользователя
     */
    private String comment;

    /**
     * Категория бюджета
     */
    private BudgetCategoryId category;

    public HoldOperation(
            HoldId holdId,
            AccountNumber account,
            LocalDate date,
            Money amount,
            OperationType type,
            String description,
            String comment,
            BudgetCategoryId category) {
        Validate.notNull(holdId);
        Validate.notNull(account);
        Validate.notNull(date);
        Validate.notNull(amount);
        Validate.notNull(type);
        Validate.notNull(description);
        this.holdId = holdId;
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.account = account;
        this.description = description;
        this.comment = comment;
        this.category = category;
    }

    public HoldId holdId() {
        return holdId;
    }

    public LocalDate date() {
        return date;
    }

    @Override
    public LocalDate recorded() {
        return date();
    }

    @Override
    public String recordId() {
        return holdId.value();
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

    @Override
    public BudgetCategoryId category() {
        return category;
    }

    public boolean matches(CardOperation other) {
        return new EqualsBuilder()
                .append(date, other.purchaseDate())
                .append(amount, other.amount())
                .isEquals();
    }

    public boolean matches(Operation other) {
        return new EqualsBuilder()
                .append(amount, other.amount())
                .append(account, other.account())
                .isEquals();
    }

    public boolean matches(Operation op, CardOperation card) {
        return op.operationId().equals(card.operationId())
                && matches(op)
                && matches(card);
    }

    @Override
    public boolean sameIdentityAs(HoldOperation other) {
        return other != null && new EqualsBuilder()
                .append(holdId, other.holdId)
                .isEquals();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final HoldOperation hold = (HoldOperation) o;
        return sameIdentityAs(hold);
    }

    @Override
    public int hashCode() {
        return Objects.hash(holdId);
    }

    @Override
    public String toString() {
        return "Hold{" + id + ", " + holdId + "}";
    }

    protected HoldOperation() {
        //for Hibernate
    }

    private Long id; // surrogate key

}
