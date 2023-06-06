package ru.vzotov.banking.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PosTerminalTest {

    @Test
    public void testCountry() {
        assertThatThrownBy(() -> {
            new Country("KRASNODAR");
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testConstructor() {
        assertThatThrownBy(() -> {
            new PosTerminal(null, null, null);
        }).as("Should not accept null arguments").isInstanceOf(NullPointerException.class);

        assertThatThrownBy(() -> {
            new PosTerminal(null, null, null, null);
        }).as("Should not accept null arguments").isInstanceOf(NullPointerException.class);

        assertThatThrownBy(() -> {
            new PosTerminal(null, null, null, null, null);
        }).as("Should not accept null arguments").isInstanceOf(NullPointerException.class);

        //allow some null arguments
        new PosTerminal(new PosTerminalId("11276718"), new Country("RUS"), null, null, new Merchant("MAGNIT MM ANT"));

        //allow empty id
        new PosTerminal(new PosTerminalId(""), new Country("RUS"), null, null, new Merchant("MAGNIT MM ANT"));

        PosTerminal terminal1 = new PosTerminal(new PosTerminalId("11276718"), new Country("RUS"), new City("SARATOV"), new Street("6 MOT"), new Merchant("MAGNIT MM ANT"));
        PosTerminal terminal2 = new PosTerminal(new PosTerminalId("11276718"), new Country("RUS"), new City("SARATOV"), new Merchant("MAGNIT MM ANT"));
        PosTerminal terminal3 = new PosTerminal(new PosTerminalId("11276718"), new Country("RUS"), new Merchant("MAGNIT MM ANT"));
        PosTerminal terminal4 = new PosTerminal(new PosTerminalId("11276718"), new Country("RUS"), new City("SARATOV"), new Street("6 MOT"), new Merchant("MAGNIT MM ANT"));

        assertThat(terminal1).isNotEqualTo(terminal2);
        assertThat(terminal1).isNotEqualTo(terminal3);
        assertThat(terminal2).isNotEqualTo(terminal3);
        assertThat(terminal4).isEqualTo(terminal1);
    }

}
