package ru.vzotov.banking.domain.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.vzotov.domain.model.Money;

import java.time.LocalDate;

@RunWith(JUnit4.class)
public class OperationTest {

    @Test
    public void testConstruct() {
        final Operation operation = new Operation(
                new OperationId("test"),
                LocalDate.of(2018, 6, 29),
                Money.rubles(1862.42d),
                OperationType.WITHDRAW,
                new Account(new AccountNumber("40817810108290123456"), PersonId.nextId()),
                "11276718 RU MAGNIT MM ANTROPOS>SARA",
                new BudgetCategory(BudgetCategoryId.of("Гипермаркет"), "Гипермаркет")
        );

        final BudgetCategory category2 = new BudgetCategory(BudgetCategoryId.of("Гипермаркет"), "Гипермаркет");
        final BudgetCategory category3 = new BudgetCategory(BudgetCategoryId.of("Гипермаркет 2"), "Гипермаркет");

        Assert.assertEquals("categories with same id must be equal", operation.category(), category2);
        Assert.assertNotEquals("categories with different id must be different", operation.category(), category3);
    }
}
