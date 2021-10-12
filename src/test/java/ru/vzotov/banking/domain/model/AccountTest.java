package ru.vzotov.banking.domain.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class AccountTest {

    @Test
    public void testConstruct() {
        final Account account1 = new Account(new AccountNumber("40817810108290123456"), PersonId.nextId());
        final Account account2 = new Account(new AccountNumber("40817810108290123456"), "Зарплатный счет", PersonId.nextId());
        Assert.assertEquals("accounts must be equal", account1, account2);
    }
}
