package ru.vzotov.banking.domain.model;

import org.apache.commons.lang3.Validate;
import ru.vzotov.ddd.shared.ValueObject;

import java.util.List;
import java.util.Objects;

/**
 * Банковский отчет
 */
public record BankReport(List<Operation> operations) implements ValueObject<BankReport> {

    public BankReport {
        Validate.notNull(operations);
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

}
