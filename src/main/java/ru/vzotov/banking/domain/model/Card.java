package ru.vzotov.banking.domain.model;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import ru.vzotov.ddd.shared.AggregateRoot;
import ru.vzotov.ddd.shared.Entity;
import ru.vzotov.person.domain.model.Owned;
import ru.vzotov.person.domain.model.PersonId;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AggregateRoot
public class Card implements Entity<Card>, Owned {

    private CardNumber cardNumber;

    private PersonId owner;

    private YearMonth validThru;

    private BankId issuer;

    private Set<AccountBinding> accounts = new HashSet<>();

    public Card(CardNumber cardNumber, PersonId owner, YearMonth validThru, BankId issuer) {
        Validate.notNull(cardNumber);
        Validate.notNull(owner);
        Validate.notNull(validThru);
        Validate.notNull(issuer);

        this.cardNumber = cardNumber;
        this.owner = owner;
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

    public BankId issuer() {
        return issuer;
    }

    public boolean bindToAccount(AccountNumber accountNumber, LocalDate from, LocalDate to) {
        AccountBinding binding = new AccountBinding(accountNumber, from, to);
        boolean mustBind = !accounts.contains(binding);
        if (mustBind) {
            accounts.add(binding);
        }
        return mustBind;
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
