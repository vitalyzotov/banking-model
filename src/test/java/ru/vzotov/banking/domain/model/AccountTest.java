package ru.vzotov.banking.domain.model;

import org.junit.jupiter.api.Test;
import ru.vzotov.person.domain.model.PersonId;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Test
    public void testConstruct() {
        final Account account1 = new Account(new AccountNumber("40817810108290123456"), PersonId.nextId());
        final Account account2 = new Account(new AccountNumber("40817810108290123456"), "Зарплатный счет", PersonId.nextId());

        assertThat(account1)
                .as("accounts must be equal").isEqualTo(account2);
    }
}
