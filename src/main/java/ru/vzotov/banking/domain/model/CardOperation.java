package ru.vzotov.banking.domain.model;

import org.apache.commons.lang3.Validate;
import ru.vzotov.ddd.shared.AggregateRoot;
import ru.vzotov.ddd.shared.Entity;
import ru.vzotov.domain.model.Money;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Bank card operation
 */
@AggregateRoot
public class CardOperation implements Entity<CardOperation> {

    /**
     * Operation identifier
     */
    private OperationId operationId;

    /**
     * Card number
     */
    private CardNumber cardNumber;

    /**
     * POS terminal information
     */
    private PosTerminal terminal;

    /**
     * Authorization date.
     * <p>
     *     When a merchant swipes a customer's credit card, the credit card terminal connects to the merchant's
     *     acquirer, or credit card processor, which verifies that the customer's account is valid and that
     *     sufficient funds are available to cover the transaction's cost.
     *     At this step, the funds are "held" and deducted from the customer's credit limit
     *     (or available bank balance, in the case of a debit card), but are not yet transferred to the merchant.
     *     At the time of the merchant's choosing, the merchant instructs the credit card machine to submit
     *     the finalized transactions to the acquirer in a "batch transfer," which begins the settlement process,
     *     where the funds are transferred from the customers' accounts to the merchant's accounts.
     * </p>
     * This date is the date when the funds are actually transferred from the customers' accounts.
     */
    private LocalDate authDate;

    /**
     * Purchase date.
     */
    private LocalDate purchaseDate;

    private Money amount;

    private String extraInfo;

    private MccCode mcc;

    public CardOperation(OperationId operationId, CardNumber cardNumber, PosTerminal terminal,
                         LocalDate authDate, LocalDate purchaseDate, Money amount, String extraInfo, MccCode mcc) {
        Validate.notNull(operationId);
        Validate.notNull(cardNumber);
        // Validate.notNull(terminal); // may be null
        Validate.notNull(authDate);
        Validate.notNull(purchaseDate);
        Validate.notNull(amount);
        Validate.notNull(mcc);

        this.operationId = operationId;
        this.cardNumber = cardNumber;
        this.terminal = terminal;
        this.authDate = authDate;
        this.purchaseDate = purchaseDate;
        this.amount = amount;
        this.extraInfo = extraInfo;
        this.mcc = mcc;
    }

    public OperationId operationId() {
        return operationId;
    }

    public CardNumber cardNumber() {
        return cardNumber;
    }

    public PosTerminal terminal() {
        return terminal;
    }

    public LocalDate authDate() {
        return authDate;
    }

    public LocalDate purchaseDate() {
        return purchaseDate;
    }

    public Money amount() {
        return amount;
    }

    public String extraInfo() {
        return extraInfo;
    }

    public MccCode mcc() {
        return mcc;
    }

    @Override
    public boolean sameIdentityAs(CardOperation that) {
        return that != null && Objects.equals(operationId, that.operationId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CardOperation that = (CardOperation) o;
        return sameIdentityAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationId);
    }

    protected CardOperation() {
        // for Hibernate
    }
}
