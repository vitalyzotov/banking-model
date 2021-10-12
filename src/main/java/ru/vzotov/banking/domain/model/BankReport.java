package ru.vzotov.banking.domain.model;

import org.apache.commons.lang.Validate;
import ru.vzotov.ddd.shared.ValueObject;

import java.util.List;
import java.util.Objects;

/**
 * Банковский отчет
 */
public class BankReport implements ValueObject<BankReport> {

    private List<Operation> operations;

    public BankReport(List<Operation> operations) {
        Validate.notNull(operations);
        this.operations = operations;
    }

    public List<Operation> operations() {
        return operations;
    }

    @Override
    public boolean sameValueAs(BankReport that) {
        return that != null && Objects.equals(operations, that.operations);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BankReport that = (BankReport) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operations);
    }
}
