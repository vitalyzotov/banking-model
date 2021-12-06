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
                new AccountNumber("40817810108290123456"),
                "11276718 RU MAGNIT MM ANTROPOS>SARA",
                BudgetCategoryId.of("Гипермаркет")
        );

        final BudgetCategoryId category2 = BudgetCategoryId.of("Гипермаркет");
        final BudgetCategoryId category3 = BudgetCategoryId.of("Гипермаркет 2");

        Assert.assertEquals("categories with same id must be equal", operation.category(), category2);
        Assert.assertNotEquals("categories with different id must be different", operation.category(), category3);
    }
}
