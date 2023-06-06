package ru.vzotov.banking.domain.model;

import org.apache.commons.lang3.Validate;
import ru.vzotov.ddd.shared.ValueObject;

import java.util.Objects;

public class PosTerminalId implements ValueObject<PosTerminalId> {

    private String value;

    public PosTerminalId(String value) {
        Validate.notNull(value);
        Validate.isTrue(value.length() == 0 || value.matches("[0-9A-Z]{1,8}"), "Wrong terminal ID: ", value);

        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean sameValueAs(PosTerminalId that) {
        return that != null && Objects.equals(value, that.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PosTerminalId that = (PosTerminalId) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    protected PosTerminalId() {
        // for Hibernate
    }
}
