package ru.vzotov.banking.domain.model;

import org.apache.commons.lang.Validate;
import ru.vzotov.ddd.shared.ValueObject;

import java.util.Objects;

public class Street implements ValueObject<Street> {

    private String name;

    public Street(String name) {
        Validate.notEmpty(name);
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean sameValueAs(Street street) {
        return street != null && Objects.equals(name, street.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Street street = (Street) o;
        return sameValueAs(street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    protected Street() {
        // for Hibernate
    }
}
