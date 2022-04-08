package ru.vzotov.banking.domain.model;

import org.apache.commons.lang.Validate;
import ru.vzotov.ddd.shared.ValueObject;

import java.util.Objects;

/**
 * IIN https://github.com/iannuttall/binlist-data
 * <p>
 * Visa (incl. VPay)	13-19	4
 * #### #### #### #### (4-4-4-4)
 * Pattern not known for 13-15 and 17-19 digit cards.
 * <p>
 * Visa Electron	16	4026, 417500, 4405, 4508, 4844, 4913, 4917
 * #### #### #### #### (4-4-4-4)
 * <p>
 * American Express	15	34, 37
 * #### ###### ##### (4-6-5)
 * <p>
 * China UnionPay	16-19	62
 * #### #### #### #### (4-4-4-4)
 * ###### ############# (6-13)
 * Pattern not known for 17-18 digit cards.
 * <p>
 * MasterCard	16	51‑55, 222100‑272099
 * #### #### #### #### (4-4-4-4)
 * <p>
 * Maestro	12-19	500000‑509999, 560000‑589999, 600000‑699999
 * #### #### ##### (4-4-5)
 * #### ###### ##### (4-6-5)
 * #### #### #### #### (4-4-4-4)
 * #### #### #### #### ### (4-4-4-4-3)
 * Pattern not known for 12, 14, 17, and 18 digit cards.
 * <p>
 * Diners Club Carte Blanche	14	300‑305
 * #### ###### #### (4-6-4)
 * <p>
 * Diners Club International	14	300‑305, 309, 36, 38‑39
 * #### ###### #### (4-6-4)
 * <p>
 * Diners Club United States & Canada	16	54, 55
 * #### #### #### #### (4-4-4-4)
 * <p>
 * Discover	16	6011, 622126‑622925, 644‑649, 65
 * #### #### #### #### (4-4-4-4)
 * <p>
 * JCB	16	3528‑3589
 * #### #### #### #### (4-4-4-4)
 * <p>
 * UATP	15	1
 * #### ##### ###### (4-5-6)
 * <p>
 * Dankort	16	5019
 * #### #### #### #### (4-4-4-4)
 * <p>
 * InterPayment	16-19	636
 * #### #### #### #### (4-4-4-4)
 * Pattern not known for 17-19 digit cards.
 */
public class CardNumber implements ValueObject<CardNumber> {

    private static final String CARDS_REGEX = "^(?:" +
            "(?<visa>4[+0-9]{12}(?:[+0-9]{3})?)|" +
            "(?<mastercard>5[1-5][+0-9]{14})|" +
            "(?<discover>6(?:011|5[0-9]{2})[+0-9]{12})|" +
            "(?<amex>3[47][+0-9]{13})|" +
            "(?<chup>62[+0-9]{14,17})|" +
            "(?<laser>(6304|6706|6709|6771)[+0-9]{12,15})|" +
            "(?<maestro>(5018|5020|5038|6304|6759|6761|6763|6799)[+0-9]{8,15})|" +
            "(?<mir>(2200|2201|2202|2203|2204)[+0-9]{12})|" +
            "(?<diners>3(?:0[0-5]|[68][0-9])[+0-9]{11})|" +
            "(?<jcb>(?:2131|1800|35[0-9]{3})[+0-9]{11})" +
            ")$";

    private String value;

    public CardNumber(String value) {
        Validate.notNull(value);
        Validate.isTrue(value.matches(CARDS_REGEX), "Wrong card number: ", value);

        this.value = value;
    }

    public String value() {
        return value;
    }

    public String displayValue() {
        int pos = 0;
        StringBuilder result = new StringBuilder();
        int[] spacing = type().spacing();
        for (int num : spacing) {
            if (pos > 0) result.append(' ');
            result.append(value, pos, pos + num);
            pos += num;
        }
        return result.toString();
    }

    public CardNumberType type() {
        return CardNumberType.of(value);
    }

    public boolean matches(CardNumber that) {
        final String v1 = this.value;
        final String v2 = that.value;
        if (v1.length() != v2.length()) return false;
        final int length = v1.length();
        for (int i = 0; i < length; i++) {
            char c1 = v1.charAt(i);
            char c2 = v2.charAt(i);
            if (c1 == '+' || c2 == '+') continue;
            if (c1 != c2) return false;
        }
        return true;
    }

    @Override
    public boolean sameValueAs(CardNumber that) {
        return that != null && Objects.equals(value, that.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardNumber that = (CardNumber) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }

    protected CardNumber() {
        // for Hibernate
    }
}
