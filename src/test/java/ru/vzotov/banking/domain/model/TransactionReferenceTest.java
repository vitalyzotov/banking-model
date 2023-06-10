package ru.vzotov.banking.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TransactionReferenceTest {

    @Test
    public void testConstructor() {
        assertThatThrownBy(() -> new TransactionReference(null))
                .as("Should not accept null arguments").isInstanceOf(NullPointerException.class);
    }

    @Test
    public void isHold() {
        TransactionReference r1 = new TransactionReference("r1");
        TransactionReference r2 = new TransactionReference("hold");
        assertThat(r1).isNotEqualTo(r2);
        assertThat(r1.isHold()).isFalse();
        assertThat(r2.isHold()).isTrue();
    }
}
