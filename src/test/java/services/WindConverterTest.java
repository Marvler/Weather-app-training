package services;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WindConverterTest {

    @Test
    void shouldCheckIfTheConversionIsValid() {
        String result = WindConverter.convertWindDegToDirection(152.25);

        assertThat(result).isEqualTo("SSE");
    }

}