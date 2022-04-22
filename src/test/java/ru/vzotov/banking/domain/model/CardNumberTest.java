package ru.vzotov.banking.domain.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.fail;

public class CardNumberTest {
    @Test
    public void testConstructor() {
        assertThat(catchThrowable(() -> {
            new CardNumber(null);
        })).as("Should not accept null arguments").isInstanceOf(Exception.class);

        assertThat(catchThrowable(() -> {
            new CardNumber("415482202203ABCD");
        })).as("Should not accept non-numeric chars").isInstanceOf(Exception.class);

        CardNumber c;

        // Visa
        c = new CardNumber("415482++++++7000");
        assertThat(c.displayValue()).isEqualTo("4154 82++ ++++ 7000");

        c = new CardNumber("4154822022035649");
        assertThat(c.isValid()).isTrue();

        c = new CardNumber("4154822022035648");
        assertThat(c.isValid()).isFalse();

        c = new CardNumber("4444333322221111");
        assertThat(c.displayValue()).isEqualTo("4444 3333 2222 1111");

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
        c = new CardNumber("371449635398431");
        assertThat(c.displayValue()).isEqualTo("3714 496353 98431");

        //Cartebleue
        c = new CardNumber("5555555555554444");
        assertThat(c.type()).isEqualTo(CardNumberType.MASTERCARD);
        assertThat(c.displayValue()).isEqualTo("5555 5555 5555 4444");

        //Dankort
        //new CardNumber("5019717010103742");

        //Diners
        new CardNumber("36700102000000");

        c = new CardNumber("36148900647913");
        assertThat(c.type()).isEqualTo(CardNumberType.DINERS_CLUB);
        assertThat(c.displayValue()).isEqualTo("3614 890064 7913");

        //Discover card
        c = new CardNumber("6011000400000000");
        assertThat(c.type()).isEqualTo(CardNumberType.DISCOVER);
        assertThat(c.displayValue()).isEqualTo("6011 0004 0000 0000");

        //JCB
        c = new CardNumber("3528000700000000");
        assertThat(c.type()).isEqualTo(CardNumberType.JCB);
        assertThat(c.displayValue()).isEqualTo("3528 0007 0000 0000");

        // China union pay
        c = new CardNumber("6250941006528599");
        assertThat(c.type()).isEqualTo(CardNumberType.CHINA_UNIONPAY);
        assertThat(c.displayValue()).isEqualTo("6250 9410 0652 8599");

        //Laser
        new CardNumber("630495060000000000");

        c = new CardNumber("630490017740292441");
        assertThat(c.type()).isEqualTo(CardNumberType.LASER_18);
        assertThat(c.displayValue()).isEqualTo("6304 9001 7740 292441");

        // Maestro
        c = new CardNumber("6759649826438453");
        assertThat(c.type()).isEqualTo(CardNumberType.MAESTRO_16);
        assertThat(c.displayValue()).isEqualTo("6759 6498 2643 8453");

        new CardNumber("6799990100000000019");

        // Mastercard
        c = new CardNumber("555957++++++0777");
        assertThat(c.type()).isEqualTo(CardNumberType.MASTERCARD);
        assertThat(c.displayValue()).isEqualTo("5559 57++ ++++ 0777");

        new CardNumber("5555555555554444");
        new CardNumber("5454545454545454");

        // MIR
        c = new CardNumber("2201877357925316");
        assertThat(c.type()).isEqualTo(CardNumberType.MIR);
        assertThat(c.displayValue()).isEqualTo("2201 8773 5792 5316");
        assertThat(c.isValid()).isTrue();

        new CardNumber("2201859609419381");
        new CardNumber("2202051661368286");
        CardNumber n1 = new CardNumber("2203737264072905");
        CardNumber n2 = new CardNumber("2200070089351218");
        assertThat(n1).isNotEqualTo(n2);
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
