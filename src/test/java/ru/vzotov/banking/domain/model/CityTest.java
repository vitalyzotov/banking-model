package ru.vzotov.banking.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CityTest {
    @Test
    public void testConstructor() {
        assertThatThrownBy(() -> new City(null))
                .as("Should not accept null arguments").isInstanceOf(Exception.class);

        City city = new City("City");
        assertThat(city.name()).isEqualTo("City");
    }

}
