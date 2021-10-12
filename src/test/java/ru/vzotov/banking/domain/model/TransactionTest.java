package ru.vzotov.banking.domain.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.vzotov.domain.model.Money;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class TransactionTest {

    @Test
    public void testConstruct() {
        OperationId id1 = new OperationId(
                LocalDate.of(2018, Month.JANUARY, 1),
                OperationType.WITHDRAW,
                new AccountNumber("40817810099910004312"),
                Money.kopecks(10), new TransactionReference("123")
        );

        OperationId id2 = new OperationId(
                LocalDate.of(2018, Month.JANUARY, 1),
                OperationType.DEPOSIT,
                new AccountNumber("40817810108290020019"),
                Money.kopecks(10), new TransactionReference("456")
        );

        Transaction t1 = new Transaction(id1, id2);
        assertThat(t1.primaryOperation()).isEqualTo(id1);
        assertThat(t1.secondaryOperation()).isEqualTo(id2);

        Transaction t2 = new Transaction(id2, id1);
        assertThat(t1).isEqualTo(t2);
    }

    @Test
    public void testMatchOperations() {
        Operation op1 = new Operation(
                new TransactionReference("123"),
                null,
                LocalDate.of(2018, Month.JANUARY, 1),
                Money.kopecks(10),
                OperationType.WITHDRAW,
                new Account(new AccountNumber("40817810099910123456"), PersonId.nextId()),
                "descr1",
                null,
                null
        );

        Operation op2 = new Operation(
                new TransactionReference("456"),
                null,
                LocalDate.of(2018, Month.JANUARY, 1),
                Money.kopecks(10),
                OperationType.DEPOSIT,
                new Account(new AccountNumber("40817810108290123456"), PersonId.nextId()),
                "descr2",
                null,
                null
        );

        Operation op3 = new Operation(
                new TransactionReference("789"),
                null,
                LocalDate.of(2018, Month.JANUARY, 3),
                Money.kopecks(500),
                OperationType.DEPOSIT,
                new Account(new AccountNumber("40817810108290123456"), PersonId.nextId()),
                "descr3",
                null,
                null
        );

        List<Transaction> transactions = Transaction.matchOperations(Arrays.asList(op1, op2, op3), 1, null);
        assertThat(transactions)
                .isNotEmpty()
                .contains(new Transaction(op1.operationId(), op2.operationId()));
    }
}
