package ru.vzotov.banking.domain.model;

import com.neovisionaries.i18n.CountryCode;
import org.apache.commons.lang.Validate;
import ru.vzotov.ddd.shared.ValueObject;

import java.util.Objects;

public class Country implements ValueObject<Country> {

    private CountryCode code;

    public Country(String code) {
        Validate.notEmpty(code);

        CountryCode c = CountryCode.getByCode(code, false);
        if (c == null && code.length() > 0 && Character.isDigit(code.charAt(0))) {
            c = CountryCode.getByCode(Integer.valueOf(code));
        }

        Validate.notNull(c);

        this.code = c;
    }

    public String code() {
        return code.getAlpha3();
    }

    @Override
    public boolean sameValueAs(Country country) {
        return country != null && code == country.code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Country country = (Country) o;
        return sameValueAs(country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    protected Country() {
        // for Hibernate
    }
}
