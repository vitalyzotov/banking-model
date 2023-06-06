package ru.vzotov.banking.domain.model;

import org.junit.jupiter.api.Test;
import ru.vzotov.domain.model.Money;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class HoldOperationTest {

    @Test
    public void testMatching() {

        AccountNumber account = new AccountNumber("40817810108290012345");

        CardOperation cardOperation = new CardOperation(
                new OperationId("test-operation-1"),
                new CardNumber("555957++++++0777"),
                new PosTerminal(new PosTerminalId("11276718"),
                        new Country("RUS"), new City("SARATOV"), new Street("6 MOT"),
                        new Merchant("MAGNIT MM ANT")),
                LocalDate.of(2018, 7, 11),
                LocalDate.of(2018, 7, 8),
                Money.rubles(2007.3d), "Google pay-9313", new MccCode("5411")
        );

        Operation operation = new Operation(
                new OperationId("test-operation-1"),
                new TransactionReference("test-operation-ref-1"), null,
                LocalDate.of(2018, 7, 11),
                Money.rubles(2007.3d),
                OperationType.WITHDRAW,
                account,
                "555957++++++0777    11276718\\RUS\\SARATOV\\6 MOT\\MAGNIT MM ANT          26.10.18 23.10.18      2007.30  RUR (Google pay-9313) MCC5411",
                null,
                null
        );

        HoldOperation hold = new HoldOperation(
                HoldId.nextId(),
                account,
                LocalDate.of(2018, 7, 8),
                Money.rubles(2007.3d),
                OperationType.WITHDRAW,
                "11276718 RU MAGNIT MM ANTROPOS.>Sar 18.07.08 18.07.08 2007.30 RUR 555957++++++0777",
                null,
                null
        );

        assertThat(hold.matches(operation, cardOperation)).isTrue();
    }
}
