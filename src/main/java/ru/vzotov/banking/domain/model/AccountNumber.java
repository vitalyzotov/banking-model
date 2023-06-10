package ru.vzotov.banking.domain.model;

import org.apache.commons.lang3.Validate;
import ru.vzotov.ddd.shared.ValueObject;

import java.util.Currency;
import java.util.Objects;

/**
 * Account number (20 digits)
 * <p>
 * <a href="https://www.banki.ru/blog/BAY/8904.php">https://www.banki.ru/blog/BAY/8904.php</a>
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
 * <ul>
 * <li><a href="http://www.consultant.ru/document/cons_doc_LAW_16053/08c1d0eacf880db80ef56f68c3469e2ea24502d7/">http://www.consultant.ru/document/cons_doc_LAW_16053/08c1d0eacf880db80ef56f68c3469e2ea24502d7/</a></li>
 * <li><a href="https://docs.cntd.ru/document/9041717">https://docs.cntd.ru/document/9041717</a></li>
 * </ul>
 * Примечательно, что в расчете контрольного ключа участвует и БИК (см. ниже).
 * Далее идет четыре цифры с кодом отделения (филиала) банка.
 * И наконец, последние 7 цифр - собственно номер счета. Он в разных банках формируется по разному.
 * У некоторых это просто порядковый номер.
 * В других банках номер состоит из персонального номера вкладчика и порядкового номера счета этого вкладчика.
 */
public class AccountNumber implements ValueObject<AccountNumber> {

    private static final char ZERO = '0';
    private static final int CHECK_INDEX = 8; //zero based

    private String number;

    public AccountNumber(String number) {
        Validate.notEmpty(number);
        Validate.isTrue(number.matches("[0-9]{20}"), "Account number length must be 20 digits:", number);

        this.number = number;
    }

    public static AccountNumber create(AccountNumberType type, Currency currency, BankId bankId, int branchCode, int localAccountNumber) {
        Validate.notNull(type);
        Validate.notNull(currency);
        Validate.notNull(bankId);
        Validate.isTrue(branchCode >= 0 && branchCode <= 9999);
        Validate.isTrue(localAccountNumber >= 0 && localAccountNumber <= 9999999);
        final char[] chars = String.format("%05d%03d%01d%04d%07d", type.value(), currency.getNumericCode(), 0, branchCode, localAccountNumber).toCharArray();
        final int checksum = checksum(chars, bankId);
        chars[CHECK_INDEX] = (char) (checksum + ZERO);
        return new AccountNumber(String.valueOf(chars));
    }

    public String number() {
        return number;
    }

    public void validate(BankId bankId) {
        int result = checksum(this.number.toCharArray(), bankId);
        if (number.charAt(CHECK_INDEX) - ZERO != result)
            throw new IllegalArgumentException("Account number " + number + " is not valid for bank ID " + bankId);
    }

    private static int checksum(char[] chars, BankId bankId) {
        int[] data = new int[23];
        int[] weights = {7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1};
        int result = 0;
        int orgNumber = bankId.orgNumber();


        data[0] = orgNumber / 100;
        data[1] = bankId.value().charAt(orgNumber == 0 ? 5 - 1 : 7) - ZERO;
        data[2] = bankId.value().charAt(orgNumber == 0 ? 6 - 1 : 8) - ZERO;

        for (int i = 0; i < chars.length; i++) {
            data[3 + i] = chars[i] - ZERO;
        }
        data[CHECK_INDEX + 3] = 0; // reset the check digit

        for (int i = 0; i < data.length; i++) {
            result += (data[i] * weights[i]) % 10;
        }
        result = ((result % 10) * 3) % 10;
        return result;
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
