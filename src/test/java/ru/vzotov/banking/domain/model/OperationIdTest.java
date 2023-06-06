package ru.vzotov.banking.domain.model;

import org.junit.jupiter.api.Test;
import ru.vzotov.domain.model.Money;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OperationIdTest {
    @Test
    public void testConstructor() {
        assertThatThrownBy(() -> {
            new OperationId(null);
        }).as("Should not accept null arguments").isInstanceOf(NullPointerException.class);

        assertThatThrownBy(() -> {
            new OperationId(null, null, null, null, null);
        }).as("Should not accept null arguments").isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testIdUniqueness() {
        OperationId id1 = new OperationId(
                LocalDate.of(2018, Month.JANUARY, 1),
                OperationType.WITHDRAW,
                new AccountNumber("40817810099910123456"),
                Money.kopecks(10), new TransactionReference("123")
        );

        OperationId id2 = new OperationId(
                LocalDate.of(2018, Month.JANUARY, 1),
                OperationType.DEPOSIT,
                new AccountNumber("40817810099910123456"),
                Money.kopecks(10), new TransactionReference("123")
        );

        OperationId id3 = new OperationId(
                LocalDate.of(2018, Month.JANUARY, 1),
                OperationType.WITHDRAW,
                new AccountNumber("40817810099910123456"),
                Money.kopecks(10), new TransactionReference("HOLD")
        );

        assertThat(id1).isNotEqualTo(id2);
        assertThat(id1).isNotEqualTo(id3);
    }
}
