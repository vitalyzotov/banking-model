package ru.vzotov.banking.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.tuple;

public class CardNumberTest {
    @Test
    public void testConstructor() {
        assertThatThrownBy(() -> new CardNumber(null))
                .as("Should not accept null arguments").isInstanceOf(Exception.class);

        assertThatThrownBy(() -> new CardNumber("415482202203ABCD"))
                .as("Should not accept non-numeric chars").isInstanceOf(Exception.class);

        //CardNumber c;

        // Visa
        assertThat(new CardNumber("415482++++++7000").displayValue()).isEqualTo("4154 82++ ++++ 7000");
        assertThat(new CardNumber("4154822022035649").isValid()).isTrue();
        assertThat(new CardNumber("4154822022035648").isValid()).isFalse();
        assertThat(new CardNumber("4444333322221111").displayValue()).isEqualTo("4444 3333 2222 1111");

        new CardNumber("4911830000000");
        new CardNumber("4917610000000000");

        // Visa Debit
        new CardNumber("4462030000000000");
        //new CardNumber("4917610000000000003");

        // Visa Electron (UK only)
        new CardNumber("4917300800000000");

        // Visa Purchasing
        new CardNumber("4484070000000000");

        //airplus
        //new CardNumber("122000000000003");

        //American Express
        assertThat(new CardNumber("371449635398431").displayValue()).isEqualTo("3714 496353 98431");

        //Cartebleue
        assertThat(new CardNumber("5555555555554444"))
                .extracting(n -> tuple(n.type(), n.displayValue()))
                .isEqualTo(tuple(CardNumberType.MASTERCARD, "5555 5555 5555 4444"));

        //Dankort
        //new CardNumber("5019717010103742");

        //Diners
        new CardNumber("36700102000000");

        assertThat(new CardNumber("36148900647913"))
                .extracting(n -> tuple(n.type(), n.displayValue()))
                .isEqualTo(tuple(CardNumberType.DINERS_CLUB, "3614 890064 7913"));

        //Discover card
        assertThat(new CardNumber("6011000400000000"))
                .extracting(c -> tuple(c.type(), c.displayValue()))
                .isEqualTo(tuple(CardNumberType.DISCOVER, "6011 0004 0000 0000"));

        //JCB
        assertThat(new CardNumber("3528000700000000"))
                .extracting(c -> tuple(c.type(), c.displayValue()))
                .isEqualTo(tuple(CardNumberType.JCB, "3528 0007 0000 0000"));

        // China union pay
        assertThat(new CardNumber("6250941006528599"))
                .extracting(c -> tuple(c.type(), c.displayValue()))
                .isEqualTo(tuple(CardNumberType.CHINA_UNIONPAY, "6250 9410 0652 8599"));

        //Laser
        new CardNumber("630495060000000000");

        assertThat(new CardNumber("630490017740292441"))
                .extracting(c -> tuple(c.type(), c.displayValue()))
                .isEqualTo(tuple(CardNumberType.LASER_18, "6304 9001 7740 292441"));

        // Maestro
        assertThat(new CardNumber("6759649826438453"))
                .extracting(c -> tuple(c.type(), c.displayValue()))
                .isEqualTo(tuple(CardNumberType.MAESTRO_16, "6759 6498 2643 8453"));

        new CardNumber("6799990100000000019");

        // Mastercard
        assertThat(new CardNumber("555957++++++0777"))
                .extracting(c -> tuple(c.type(), c.displayValue()))
                .isEqualTo(tuple(CardNumberType.MASTERCARD, "5559 57++ ++++ 0777"));

        new CardNumber("5555555555554444");
        new CardNumber("5454545454545454");

        // MIR
        assertThat(new CardNumber("2201877357925316"))
                .extracting(c -> tuple(c.type(), c.displayValue(), c.isValid()))
                .isEqualTo(tuple(CardNumberType.MIR, "2201 8773 5792 5316", true));

        new CardNumber("2201859609419381");
        new CardNumber("2202051661368286");
        assertThat(new CardNumber("2203737264072905")).isNotEqualTo(new CardNumber("2200070089351218"));
    }

    @Test
    public void testMatches() {
        CardNumber n1 = new CardNumber("415482++++++7000");
        CardNumber n2 = new CardNumber("4154822022037000");
        CardNumber n3 = new CardNumber("4154822022037010");
        assertThat(n1.matches(n2)).isTrue();
        assertThat(n2.matches(n1)).isTrue();
        assertThat(n3.matches(n1)).isFalse();
        assertThat(n1.matches(n3)).isFalse();
    }
}
