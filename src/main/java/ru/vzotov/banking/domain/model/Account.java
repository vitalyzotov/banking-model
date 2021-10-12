package ru.vzotov.banking.domain.model;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import ru.vzotov.ddd.shared.AggregateRoot;
import ru.vzotov.ddd.shared.Entity;

import java.util.Currency;
import java.util.Objects;

/**
 * Счет
 */
@AggregateRoot
public class Account implements Entity<Account> {

    /**
     * Номер счета
     */
    private AccountNumber accountNumber;

    /**
     * Название счета для отображения в интерфейсе
     */
    private String name;

    /**
     * Банк, в котором заведен этот счет.
     * Для наличных счетов будет null.
     */
    private BankId bankId;

    /**
     * Валюта счета
     */
    private Currency currency;

    /**
     * Идентификатор счета во внешних системах
     */
    private AccountAliases aliases;

    /**
     * Владелец счета
     */
    private PersonId owner;

    public Account(AccountNumber accountNumber, PersonId owner) {
        this(accountNumber, null, null, null, owner);
    }

    public Account(AccountNumber accountNumber, String name, PersonId owner) {
        this(accountNumber, name, null, null, owner);
    }

    public Account(AccountNumber accountNumber, String name, BankId bankId, Currency currency, PersonId owner) {
        this(accountNumber, name, bankId, currency, owner, null);
    }

    public Account(AccountNumber accountNumber, String name, BankId bankId, Currency currency, PersonId owner, AccountAliases aliases) {
        Validate.notNull(accountNumber);
        Validate.notNull(owner);
        this.accountNumber = accountNumber;
        this.name = name;
        this.bankId = bankId;
        this.currency = currency;
        this.aliases = aliases;
        this.owner = owner;
    }

    public PersonId owner() {
        return owner;
    }

    public AccountAliases aliases() {
        return aliases;
    }

    public void setAliases(AccountAliases aliases) {
        this.aliases = aliases;
    }

    public AccountNumber accountNumber() {
        return accountNumber;
    }

    public String name() {
        return name;
    }

    public BankId bankId() {
        return bankId;
    }

    public void setBankId(BankId bankId) {
        this.bankId = bankId;
    }

    public Currency currency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void rename(String newName) {
        this.name = newName;
    }

    @Override
    public boolean sameIdentityAs(Account other) {
        return other != null && new EqualsBuilder()
                .append(accountNumber, other.accountNumber)
                .isEquals();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Account account = (Account) o;
        return sameIdentityAs(account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }

    protected Account() {
        // for Hibernate
    }

}
