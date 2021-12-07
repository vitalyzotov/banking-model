package ru.vzotov.banking.domain.model;

import org.apache.commons.lang.Validate;
import ru.vzotov.ddd.shared.DomainEvent;

import java.util.Objects;

public class TransactionCreatedEvent implements DomainEvent<TransactionCreatedEvent> {
    private final OperationId primary;
    private final OperationId secondary;

    public TransactionCreatedEvent(OperationId primary, OperationId secondary) {
        Validate.notNull(primary);
        Validate.notNull(secondary);
        this.primary = primary;
        this.secondary = secondary;
    }

    public OperationId primary() {
        return primary;
    }

    public OperationId secondary() {
        return secondary;
    }


    @Override
    public boolean sameEventAs(TransactionCreatedEvent other) {
        return other != null && other.primary.equals(this.primary) && other.secondary.equals(this.secondary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionCreatedEvent that = (TransactionCreatedEvent) o;
        return sameEventAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primary, secondary);
    }
}
