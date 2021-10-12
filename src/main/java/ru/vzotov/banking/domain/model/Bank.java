package ru.vzotov.banking.domain.model;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import ru.vzotov.ddd.shared.AggregateRoot;
import ru.vzotov.ddd.shared.Entity;

import java.util.Objects;

@AggregateRoot
public class Bank implements Entity<Bank> {

    /**
     * БИК
     */
    private BankId bankId;

    /**
     * Наименование
     */
    private String name;

    /**
     * Сокращённое фирменное наименование
     */
    private String shortName;

    /**
     * Полное фирменное наименование
     */
    private String longName;

    public Bank(BankId bankId, String name, String shortName, String longName) {
        Validate.notNull(bankId);
        Validate.notEmpty(name);
        Validate.notEmpty(shortName);
        Validate.notEmpty(longName);
        this.bankId = bankId;
        this.name = name;
        this.shortName = shortName;
        this.longName = longName;
    }

    public BankId bankId() {
        return bankId;
    }

    public String name() {
        return name;
    }

    public String shortName() {
        return shortName;
    }

    public String longName() {
        return longName;
    }

    public void rename(String name, String shortName, String longName) {
        Validate.notEmpty(name);
        Validate.notEmpty(shortName);
        Validate.notEmpty(longName);
        this.name = name;
        this.shortName = shortName;
        this.longName = longName;
    }

    @Override
    public boolean sameIdentityAs(Bank other) {
        return other != null && new EqualsBuilder()
                .append(bankId, other.bankId)
                .isEquals();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Bank that = (Bank) o;
        return sameIdentityAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankId);
    }

    @Override
    public String toString() {
        return name;
    }

    protected Bank() {
        // for Hibernate
    }

    private Long id; // surrogate key
}
