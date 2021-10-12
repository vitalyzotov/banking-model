package ru.vzotov.banking.domain.model;

import org.apache.commons.lang.Validate;
import ru.vzotov.ddd.shared.ValueObject;

import java.util.Objects;

/**
 * Номер счета (20 цифр)
 * https://www.banki.ru/blog/BAY/8904.php
 * <p>
 * Номер счета включает двадцать знаков. Например: 40817810570000123456
 * </p>
 * <p>
 * Первые 5 знаков для вкладчиков банка могут означать следующее:
 * </p>
 * <ul>
 * <li>40817 - Текущие и карточные счета физических лиц.</li>
 * <li>42301 - Депозиты до востребования</li>
 * <li>42302 - Депозиты на срок до 30 дней</li>
 * <li>42303 - Депозиты на срок от 31 до 90 дней</li>
 * <li>42304 - Депозиты на срок от 91 до 180 дней</li>
 * <li>42305 - Депозиты на срок от 181 дня до 1 года</li>
 * <li>42306 - Депозиты на срок от 1 года до 3 лет</li>
 * <li>42307 - Депозиты на срок свыше 3 лет</li>
 * </ul>
 * <p>Далее идет три знака с обозначением валюты счета:</p>
 * <ul>
 * <li>810 - российский рубль (RUR),
 * <li>840 - американский доллар (USD),
 * <li>978 - евро (EUR) и т.д.
 * </ul>
 * Далее идет один знак с контрольной суммой (позволяет проверить, что все прочие цифры введены правильно).
 * Если интересно, посмотреть алгоритм расчета контрольного ключа можно посмотреть здесь:
 * http://www.consultant.ru/document/cons_doc_LAW_16053/08c1d0eacf880db80ef56f68c3469e2ea24502d7/
 * Примечательно, что в расчете контрольного ключа участвует и БИК (см. ниже).
 * Далее идет четыре цифры с кодом отделения (филиала) банка.
 * И наконец, последние 7 цифр - собственно номер счета. Он в разных банках формируется по разному.
 * У некоторых это просто порядковый номер.
 * В других банках номер состоит из персонального номера вкладчика и порядкового номера счета этого вкладчика.
 */
public class AccountNumber implements ValueObject<AccountNumber> {

    private String number;

    public AccountNumber(String number) {
        Validate.notEmpty(number);
        Validate.isTrue(number.matches("[0-9]{20}"), "Account number length must be 20 digits:", number);

        this.number = number;
    }

    public String number() {
        return number;
    }

    @Override
    public boolean sameValueAs(AccountNumber that) {
        return that != null && Objects.equals(number, that.number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final AccountNumber that = (AccountNumber) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return number;
    }

    protected AccountNumber() {
        // for Hibernate
    }
}
