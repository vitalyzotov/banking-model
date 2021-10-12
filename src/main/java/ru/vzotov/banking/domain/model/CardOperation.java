package ru.vzotov.banking.domain.model;

import org.apache.commons.lang.Validate;
import ru.vzotov.ddd.shared.AggregateRoot;
import ru.vzotov.ddd.shared.Entity;
import ru.vzotov.domain.model.Money;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Операция по банковской карте
 */
@AggregateRoot
public class CardOperation implements Entity<CardOperation> {

    /**
     * Идентификатор, соответствует идентификатору операции
     */
    private OperationId operationId;

    /**
     * Номер карты
     */
    private CardNumber cardNumber;

    /**
     * Информация о терминале
     */
    private PosTerminal terminal;

    /**
     * Дата авторизации. Соответствует дате снятия блокировки средств и дате фактического списания средств со счета.
     */
    private LocalDate authDate;

    /**
     * Дата покупки.
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
