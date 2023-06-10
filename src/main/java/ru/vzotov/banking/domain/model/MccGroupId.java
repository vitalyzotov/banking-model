package ru.vzotov.banking.domain.model;

import org.apache.commons.lang3.Validate;
import ru.vzotov.ddd.shared.ValueObject;

import java.util.Objects;
import java.util.UUID;

public class MccGroupId implements ValueObject<MccGroupId> {

    private String value;

    public MccGroupId(String value) {
        Validate.notEmpty(value);
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static MccGroupId nextId() {
        return new MccGroupId(UUID.randomUUID().toString());
    }

    @Override
    public boolean sameValueAs(MccGroupId that) {
        return that != null && Objects.equals(value, that.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MccGroupId that = (MccGroupId) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }

    protected MccGroupId() {
        // for Hibernate
    }
}
