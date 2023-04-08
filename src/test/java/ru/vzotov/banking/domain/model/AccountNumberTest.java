package ru.vzotov.banking.domain.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;
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

        assertThat(AccountNumber.create(
                AccountNumberType.INDIVIDUAL_CURRENT_ACCOUNT,
                Currency.getInstance("RUR"),
                BankId.ALFABANK,
                829,
                123456
        )).isEqualTo(new AccountNumber("40817810008290123456"));
    }

    @Test
    public void testCreate() {
        AccountNumber number = AccountNumber.create(
                AccountNumberType.INDIVIDUAL_CURRENT_ACCOUNT,
                Currency.getInstance("RUR"),
                BankId.OZON,
                927,
                6355862
        );
        assertThat(number).isEqualTo(new AccountNumber("40817810209276355862"));
    }
}
