package ru.vzotov.banking.domain.model;

import org.apache.commons.lang3.Validate;
import ru.vzotov.ddd.shared.ValueObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AccountAliases implements ValueObject<AccountAliases> {

    private List<String> value;

    public AccountAliases(List<String> value) {
        Validate.notNull(value);
        this.value = Collections.unmodifiableList(value);
    }

    public List<String> value() {
        return value;
    }

    public boolean contains(String alias) {
        return value.contains(alias);
    }

    @Override
    public boolean sameValueAs(AccountAliases that) {
        return that != null && Objects.equals(value, that.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountAliases that = (AccountAliases) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value
                .stream()
                .map(s -> "[" + s + "]")
                .collect(Collectors.joining(","));
    }

    public static AccountAliases fromString(String strValue) {
        return new AccountAliases(Arrays.stream(strValue.split(","))
                .map(String::trim)
                .map(s -> {
                    Validate.isTrue(s.charAt(0) == '[');
                    Validate.isTrue(s.charAt(s.length() - 1) == ']');
                    return s.substring(1, s.length() - 1);
                })
                .collect(Collectors.toList()));
    }

    protected AccountAliases() {
        //for Hibernate
    }
}
