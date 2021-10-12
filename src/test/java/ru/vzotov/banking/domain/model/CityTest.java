package ru.vzotov.banking.domain.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.fail;

public class CityTest {
    @Test
    public void testConstructor() {
        assertThat(catchThrowable(() -> {
            new City(null);
        })).as("Should not accept null arguments").isInstanceOf(Exception.class);

        City city = new City("City");
        assertThat(city.name()).isEqualTo("City");
    }

}
