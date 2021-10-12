package ru.vzotov.banking.domain.model;

import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.fail;

public class BankReportTest {
    @Test
    public void testConstructor() {
        assertThat(catchThrowable(() -> {
            new BankReport(null);
        })).as("Should not accept null arguments").isInstanceOf(Exception.class);

        BankReport address = new BankReport(Collections.emptyList());
        assertThat(address.operations()).isEmpty();
    }

}
