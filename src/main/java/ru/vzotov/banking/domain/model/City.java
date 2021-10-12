package ru.vzotov.banking.domain.model;

import org.apache.commons.lang.Validate;
import ru.vzotov.ddd.shared.ValueObject;

import java.util.Objects;

public class City implements ValueObject<City> {

    private String name;

    public City(String name) {
        Validate.notEmpty(name);
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean sameValueAs(City city) {
        return city != null && Objects.equals(name, city.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final City city = (City) o;
        return sameValueAs(city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    protected City() {
        // for Hibernate
    }
}
