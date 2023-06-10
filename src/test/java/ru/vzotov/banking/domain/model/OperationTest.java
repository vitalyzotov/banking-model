package ru.vzotov.banking.domain.model;

import org.junit.jupiter.api.Test;
import ru.vzotov.domain.model.Money;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

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

        assertThat(operation.category())
                .as("categories with same id must be equal").isEqualTo(category2);
        assertThat(operation.category())
                .as("categories with different id must be different").isNotEqualTo(category3);
    }
}
