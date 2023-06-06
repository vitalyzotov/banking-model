package ru.vzotov.banking.domain.model;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import ru.vzotov.ddd.shared.AggregateRoot;
import ru.vzotov.ddd.shared.Entity;
import ru.vzotov.person.domain.model.Owned;
import ru.vzotov.person.domain.model.PersonId;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AggregateRoot
public class Card implements Entity<Card>, Owned {

    /**
     * Card number
     */
    private CardNumber cardNumber;

    /**
     * Card holder
     */
    private PersonId owner;

    /**
     * Valid thru
     */
    private YearMonth validThru;

    /**
     * Card issuer
     */
    private BankId issuer;

    /**
     * Bindings of card to accounts
     */
    private Set<AccountBinding> accounts = new HashSet<>();

    public Card(CardNumber cardNumber, PersonId holder, YearMonth validThru, BankId issuer) {
        Validate.notNull(cardNumber);
        Validate.notNull(holder);
        Validate.notNull(validThru);
        Validate.notNull(issuer);

        this.cardNumber = cardNumber;
        this.owner = holder;
        this.validThru = validThru;
        this.issuer = issuer;
    }

    public CardNumber cardNumber() {
        return cardNumber;
    }

    public PersonId owner() {
        return owner;
    }

    public YearMonth validThru() {
        return validThru;
    }

    public void setValidThru(YearMonth validThru) {
        this.validThru = validThru;
    }

    public BankId issuer() {
        return issuer;
    }

    public Set<AccountBinding> accounts() {
        return Collections.unmodifiableSet(accounts);
    }

    public boolean bindToAccount(AccountNumber accountNumber, LocalDate from, LocalDate to) {
        //todo: check that bindings are not ambiguous
        AccountBinding binding = new AccountBinding(accountNumber, from, to);
        boolean canBind = !accounts.contains(binding);
        if (canBind) {
            accounts.add(binding);
        }
        return canBind;
    }

    public boolean unbindAccount(AccountNumber accountNumber, LocalDate from, LocalDate to) {
        AccountBinding binding = new AccountBinding(accountNumber, from, to);
        return accounts.remove(binding);
    }

    public void unbindAll() {
        accounts.clear();
    }

    @Override
    public boolean sameIdentityAs(Card other) {
        return other != null && new EqualsBuilder()
                .append(cardNumber, other.cardNumber)
                .isEquals();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Card that = (Card) o;
        return sameIdentityAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber);
    }

    @Override
    public String toString() {
        return cardNumber.toString();
    }

    protected Card() {
        // for Hibernate
    }

    private Long id;
}
