package services;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WindConverterTest {

    @Test
    void shouldCheckIfTheConversionIsValid() {
        String result = WindConverter.convertWindDegToDirection(152.25);

        assertThat(result).isEqualTo("SSE");
    }

    @Test
    void shouldCheckIfTheConversionIsNotValid() {
        String result = WindConverter.convertWindDegToDirection(850.75);

        assertThat(result).isEqualTo("?");
    }

}