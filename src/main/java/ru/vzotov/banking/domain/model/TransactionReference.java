package ru.vzotov.banking.domain.model;

import org.apache.commons.lang.Validate;
import ru.vzotov.ddd.shared.ValueObject;

import java.util.Objects;

public class TransactionReference implements ValueObject<TransactionReference> {

    private static final String HOLD = "HOLD";

    private String reference;

    public TransactionReference(String reference) {
        Validate.notNull(reference);
        Validate.isTrue(reference.length() <= 32, "Wrong transaction reference:", reference);

        this.reference = reference;
    }

    public String reference() {
        return reference;
    }

    public boolean isHold() {
        return HOLD.equalsIgnoreCase(reference);
    }

    @Override
    public boolean sameValueAs(TransactionReference that) {
        return that != null
                && Objects.equals(this.reference, that.reference);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionReference that = (TransactionReference) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }

    @Override
    public String toString() {
        return reference;
    }

    protected TransactionReference() {
        //for Hibernate
    }
}
