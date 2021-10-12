package ru.vzotov.banking.domain.model;

import ru.vzotov.domain.model.Money;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.time.temporal.ChronoUnit.DAYS;

public class SimpleTransactionMatcher implements TransactionMatcher {
    @Override
    public List<Transaction> match(Collection<Operation> operations, int threshold, Collection<Set<Operation>> ambiguous) {
        final List<Transaction> result = new ArrayList<>();
        final Map<Money, Map<LocalDate, Set<Operation>>> idx = new HashMap<>();

        // индексируем операции
        operations.forEach(op -> {
            Map<LocalDate, Set<Operation>> idx2;
            boolean processed = false;

            if (idx.containsKey(op.amount())) {
                idx2 = idx.get(op.amount());
                for (Map.Entry<LocalDate, Set<Operation>> entry : idx2.entrySet()) {
                    processed = Math.abs(DAYS.between(entry.getKey(), op.date())) <= threshold;
                    if (processed) {
                        entry.getValue().add(op);
                        break;
                    }
                }
            } else {
                idx2 = new LinkedHashMap<>();
            }

            if (!processed) {
                final Set<Operation> set = new LinkedHashSet<>();
                set.add(op);
                idx2.put(op.date(), set);
            }
            idx.put(op.amount(), idx2);
        });

        idx.forEach((amount, idx2) -> {
            idx2.forEach((date, set) -> {
                if (set.size() == 2) {
                    Iterator<Operation> iterator = set.iterator();
                    Operation primary = iterator.next();
                    Operation secondary = iterator.next();
                    if (!primary.account().accountNumber().equals(secondary.account().accountNumber())) {
                        result.add(new Transaction(primary.operationId(), secondary.operationId()));
                    }
                } else if (ambiguous != null && set.size() > 2) {
                    ambiguous.add(set);
                }
            });
        });

        return result;
    }
}
