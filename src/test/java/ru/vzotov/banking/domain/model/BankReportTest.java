package ru.vzotov.banking.domain.model;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BankReportTest {
    @Test
    public void testConstructor() {
        assertThatThrownBy(() -> new BankReport(null))
                .as("Should not accept null arguments").isInstanceOf(Exception.class);

        BankReport address = new BankReport(Collections.emptyList());
        assertThat(address).extracting(BankReport::operations)
                .asList().isEmpty();
    }

}
