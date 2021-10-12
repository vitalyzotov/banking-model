package ru.vzotov.banking.domain.model;

import org.apache.commons.lang.Validate;
import ru.vzotov.ddd.shared.ValueObject;

import java.util.Objects;

public class Merchant implements ValueObject<Merchant> {

    private String name;

    public Merchant(String name) {
        Validate.notEmpty(name);
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean sameValueAs(Merchant merchant) {
        return merchant != null && Objects.equals(name, merchant.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Merchant merchant = (Merchant) o;
        return sameValueAs(merchant);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    protected Merchant() {
        //for Hibernate
    }
}
