package ru.vzotov.banking.domain.model;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import ru.vzotov.ddd.shared.ValueObject;
import ru.vzotov.domain.model.Money;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Идентификатор финансовой операции
 */
public class OperationId implements ValueObject<OperationId> {

    private String id;

    public OperationId(String id) {
        init(id);
    }

    public OperationId(LocalDate date, OperationType type, AccountNumber accountNumber, Money amount, TransactionReference transactionReference) {
        Validate.notNull(date);
        Validate.notNull(type);
        Validate.notNull(accountNumber);
        Validate.notNull(amount);
        Validate.notNull(transactionReference);

        init(date.format(DateTimeFormatter.ISO_LOCAL_DATE) +
                type.symbol() +
                accountNumber.number() +
                amount.rawAmount() +
                amount.currency().getCurrencyCode()+
                "@"+
                transactionReference.reference()
        );
    }

    private void init(String id) {
        Validate.notNull(id);
        this.id = id;
    }

    public String idString() {
        return id;
    }

    @Override
    public boolean sameValueAs(OperationId other) {
        return other != null && new EqualsBuilder()
                .append(id, other.id)
                .isEquals();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OperationId that = (OperationId) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id;
    }

    protected OperationId() {
        // for Hibernate
    }
}
