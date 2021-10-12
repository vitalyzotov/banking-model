package ru.vzotov.banking.domain.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface TransactionMatcher {

    /**
     * Сопоставление операций в транзакции.
     * Сопоставление производится по сумме и дате.
     * Сумма должна точно совпадать у двух транзакций.
     * Дата может отличаться не более чем на количество дней, указанное в параметре threshold
     * Тип операции должен быть противоположен
     *
     * @param operations список операций для сопоставления
     * @param threshold  максимальное расхождение дат операций, дней
     * @param ambiguous  список операций, для которых система не может автоматически определить соответствие
     * @return список транзакций
     */
    List<Transaction> match(Collection<Operation> operations, int threshold, Collection<Set<Operation>> ambiguous);

}
