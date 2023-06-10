package ru.vzotov.banking.domain.model;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import ru.vzotov.ddd.shared.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Привязка к счету.
 * Используется для сопоставления банковских карт и счетов
 */
public class AccountBinding implements ValueObject<AccountBinding> {

    private AccountNumber accountNumber;

    private LocalDate from;

    private LocalDate to;

    public AccountBinding(AccountNumber accountNumber, LocalDate from, LocalDate to) {
        Validate.notNull(accountNumber);
        Validate.notNull(from);
        Validate.notNull(to);
        Validate.isTrue(from.isEqual(to) || from.isBefore(to));
        this.accountNumber = accountNumber;
        this.from = from;
        this.to = to;
    }

    public AccountNumber accountNumber() {
        return accountNumber;
    }

    public LocalDate from() {
        return from;
    }

    public LocalDate to() {
        return to;
    }

    @Override
    public boolean sameValueAs(AccountBinding that) {
        return that != null && new EqualsBuilder()
                .append(accountNumber, that.accountNumber)
                .append(from, that.from)
                .append(to, that.to)
                .isEquals();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountBinding that = (AccountBinding) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, from, to);
    }

    @Override
    public String toString() {
        return accountNumber.toString() + "[" + from + "," + to + "]";
    }

    protected AccountBinding() {
        // for Hibernate
    }

    private Long id; // surrogate key
}
