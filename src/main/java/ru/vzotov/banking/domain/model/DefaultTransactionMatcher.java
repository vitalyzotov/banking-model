package ru.vzotov.banking.domain.model;

import ru.vzotov.domain.model.Money;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.stream.Collectors.groupingBy;

public class DefaultTransactionMatcher implements TransactionMatcher {
    @Override
    public List<Transaction> match(Collection<Operation> operations, int threshold, Collection<Set<Operation>> ambiguous) {
        List<Transaction> result = new ArrayList<>();

        List<Operation> deposit = operations.stream()
                .filter(op -> OperationType.DEPOSIT.equals(op.type()))
                .toList();

        // create index of withdraw operations by amount and date
        Map<Money, Map<LocalDate, List<Operation>>> withdraw = operations.stream()
                .filter(op -> OperationType.WITHDRAW.equals(op.type()))
                .collect(groupingBy(Operation::amount, groupingBy(Operation::date, Collectors.toList())));

        for (Operation primary : deposit) {
            final Map<LocalDate, List<Operation>> candidates = Optional.ofNullable(withdraw.get(primary.amount()))
                    .orElse(Collections.emptyMap());
            //Math.abs(DAYS.between(entry.getKey(), op.date())) <= threshold;
            List<Operation> secondaries = candidates.entrySet().stream()
                    .filter(entry -> Math.abs(DAYS.between(entry.getKey(), primary.date())) <= threshold)
                    .map(Map.Entry::getValue)
                    .flatMap(Collection::stream)
                    .sorted(Comparator.comparingLong(operation -> Math.abs(DAYS.between(operation.date(), primary.date()))))
                    .toList();

            if (secondaries.size() == 1) {
                result.add(new Transaction(primary.operationId(), secondaries.get(0).operationId()));
            } else if (secondaries.size() > 1) {
                if (ambiguous != null) {
                    ambiguous.add(Stream.concat(Stream.of(primary), secondaries.stream()).collect(Collectors.toSet()));
                }
            }
        }

        return result;
    }
}
