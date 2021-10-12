package ru.vzotov.banking.domain.model;

import org.junit.Test;
import ru.vzotov.domain.model.Money;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class CardOperationTest {
    @Test
    public void testConstructor() {
        assertThat(catchThrowable(() -> {
            new CardOperation(null, null, null, null, null, null, null, null);
        })).as("Should not accept null arguments").isInstanceOf(Exception.class);

        CardOperation cardOperation = new CardOperation(
                new OperationId("test-operation-1"),
                new CardNumber("555957++++++0777"),
                new PosTerminal(new PosTerminalId("11276718"), new Country("RUS"), new City("SARATOV"), new Street("6 MOT"), new Merchant("MAGNIT MM ANT")),
                LocalDate.of(2018, 7, 11),
                LocalDate.of(2018, 7, 8),
                Money.rubles(2007.3d), "Google pay-9313", new MccCode("5411")
        );

        assertThat(cardOperation.operationId().idString()).isEqualTo("test-operation-1");

        // допускается создавать операции без сведений о терминале
        CardOperation unknownTerminal = new CardOperation(
                new OperationId("test-operation-1"),
                new CardNumber("555957++++++0777"),
                null,
                LocalDate.of(2018, 7, 11),
                LocalDate.of(2018, 7, 8),
                Money.rubles(2007.3d), "Google pay-9313", new MccCode("5411")
        );
        assertThat(unknownTerminal.operationId().idString()).isEqualTo("test-operation-1");
    }

}
