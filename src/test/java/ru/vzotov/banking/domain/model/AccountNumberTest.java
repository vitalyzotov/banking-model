package ru.vzotov.banking.domain.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.assertj.core.api.Assertions.assertThatCode;

@RunWith(JUnit4.class)
public class AccountNumberTest {

    @Test
    public void testValidate() {
        assertThatCode(() -> new AccountNumber("40817810108290123456").validate(BankId.ALFABANK))
                .as("Invalid checksum not throwing error")
                .isInstanceOf(IllegalArgumentException.class);

        assertThatCode(() -> new AccountNumber("40817810008290123456").validate(BankId.ALFABANK))
                .as("Valid checksum throwing error")
                .doesNotThrowAnyException();
    }
}
