package ru.vzotov.banking.domain.model;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import ru.vzotov.ddd.shared.AggregateRoot;
import ru.vzotov.ddd.shared.Entity;

import java.util.Collection;
import java.util.List;
import java.util.Set;


/**
 * Финансовая транзакция.
 * Объединяет две операции (по разным счетам) и фиксирует в системе двойную запись.
 */
@AggregateRoot
public class Transaction implements Entity<Transaction> {
    private OperationId primaryOperation;
    private OperationId secondaryOperation;

    public Transaction(OperationId primaryOperation, OperationId secondaryOperation) {
        Validate.notNull(primaryOperation);
        Validate.notNull(secondaryOperation);
        this.primaryOperation = primaryOperation;
        this.secondaryOperation = secondaryOperation;
    }

    public OperationId primaryOperation() {
        return primaryOperation;
    }

    public OperationId secondaryOperation() {
        return secondaryOperation;
    }

    /**
     * Автоматическое сопоставление операций в транзакции.
     * Сопоставление производится по сумме и дате.
     * Сумма должна точно совпадать у двух транзакций.
     * Дата может отличаться не более чем на количество дней, указанное в параметре threshold
     *
     * @param operations список операций для сопоставления
     * @param threshold  максимальное расхождение дат операций, дней
     * @param ambiguous  список операций, для которых система не может автоматически определить соответствие
     * @return список транзакций
     */
    public static List<Transaction> matchOperations(Collection<Operation> operations, int threshold, Collection<Set<Operation>> ambiguous) {
        return new DefaultTransactionMatcher().match(operations, threshold, ambiguous);
    }

    @Override
    public boolean sameIdentityAs(Transaction other) {
        return other != null && (
                new EqualsBuilder()
                        .append(primaryOperation, other.primaryOperation)
                        .append(secondaryOperation, other.secondaryOperation)
                        .isEquals()
                        ||
                        new EqualsBuilder()
                                .append(primaryOperation, other.secondaryOperation)
                                .append(secondaryOperation, other.primaryOperation)
                                .isEquals()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Transaction trx = (Transaction) o;
        return sameIdentityAs(trx);
    }

    @Override
    public int hashCode() {
        return primaryOperation.hashCode() ^ secondaryOperation.hashCode();
    }


    protected Transaction() {
        // for Hibernate
    }

    private Long id; // surrogate key
}
