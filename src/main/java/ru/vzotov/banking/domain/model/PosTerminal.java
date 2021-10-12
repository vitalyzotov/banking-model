package ru.vzotov.banking.domain.model;

import org.apache.commons.lang.Validate;
import ru.vzotov.ddd.shared.ValueObject;

import java.util.Objects;

public class PosTerminal implements ValueObject<PosTerminal> {

    private PosTerminalId terminalId;

    private Country country;

    private City city;

    private Street street;

    private Merchant merchant;

    public PosTerminal(PosTerminalId terminalId, Country country, City city, Street street, Merchant merchant) {
        Validate.notNull(terminalId);
        Validate.notNull(country);
        Validate.notNull(merchant);

        this.terminalId = terminalId;
        this.country = country;
        this.city = city;
        this.street = street;
        this.merchant = merchant;
    }

    public PosTerminal(PosTerminalId terminalId, Country country, City city, Merchant merchant) {
        this(terminalId, country, city, null, merchant);
    }

    public PosTerminal(PosTerminalId terminalId, Country country, Merchant merchant) {
        this(terminalId, country, null, null, merchant);
    }

    public PosTerminalId terminalId() {
        return terminalId;
    }

    public Country country() {
        return country;
    }

    public City city() {
        return city;
    }

    public Street street() {
        return street;
    }

    public Merchant merchant() {
        return merchant;
    }

    @Override
    public boolean sameValueAs(PosTerminal that) {
        return that != null
                && Objects.equals(terminalId, that.terminalId)
                && Objects.equals(country, that.country)
                && Objects.equals(city, that.city)
                && Objects.equals(street, that.street)
                && Objects.equals(merchant, that.merchant)
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PosTerminal that = (PosTerminal) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(terminalId);
    }

    protected PosTerminal() {
        // for Hibernate
    }
}
