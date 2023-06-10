package ru.vzotov.banking.domain.model;

import org.apache.commons.lang3.Validate;
import ru.vzotov.ddd.shared.ValueObject;

import java.util.Objects;

/**
 * Положение Банка России от 27.02.2017 N 579-П (ред. от 25.04.2022) "О Плане счетов бухгалтерского учета
 * для кредитных организаций и порядке его применения" (Зарегистрировано в Минюсте России 20.03.2017 N 46021)
 * <a href="https://www.consultant.ru/document/cons_doc_LAW_213488/">https://www.consultant.ru/document/cons_doc_LAW_213488/</a>
 */
public class AccountNumberType implements ValueObject<AccountNumberType> {

    public static final AccountNumberType INDIVIDUAL_CURRENT_ACCOUNT = new AccountNumberType(40817);

    public static final AccountNumberType DEPOSIT_DEMAND_ACCOUNT = new AccountNumberType(42301);
    public static final AccountNumberType DEPOSIT_0_30_ACCOUNT = new AccountNumberType(42302);
    public static final AccountNumberType DEPOSIT_31_90_ACCOUNT = new AccountNumberType(42303);
    public static final AccountNumberType DEPOSIT_91_180_ACCOUNT = new AccountNumberType(42304);
    public static final AccountNumberType DEPOSIT_181_1Y_ACCOUNT = new AccountNumberType(42305);
    public static final AccountNumberType DEPOSIT_1Y_3Y_ACCOUNT = new AccountNumberType(42306);
    public static final AccountNumberType DEPOSIT_OVER_3Y_ACCOUNT = new AccountNumberType(42307);

    public static final AccountNumberType OTHER_DEMAND_ACCOUNT = new AccountNumberType(42309);
    public static final AccountNumberType OTHER_0_30_ACCOUNT = new AccountNumberType(42310);
    public static final AccountNumberType OTHER_31_90_ACCOUNT = new AccountNumberType(42311);
    public static final AccountNumberType OTHER_91_180_ACCOUNT = new AccountNumberType(42312);
    public static final AccountNumberType OTHER_181_1Y_ACCOUNT = new AccountNumberType(42313);
    public static final AccountNumberType OTHER_1Y_3Y_ACCOUNT = new AccountNumberType(42314);
    public static final AccountNumberType OTHER_OVER_3Y_ACCOUNT = new AccountNumberType(42315);

    private final int value;

    public AccountNumberType(int value) {
        Validate.isTrue(value >= 10207 && value <= 99999);
        this.value = value;
    }

    public int value() {
        return value;
    }

    @Override
    public boolean sameValueAs(AccountNumberType that) {
        return that != null && Objects.equals(value, that.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final AccountNumberType that = (AccountNumberType) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

}
