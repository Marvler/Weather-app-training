import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidationTest {

    @Test
    void shouldCheckIfTheFieldCityIsNotEmpty() {
        Validation validation = new Validation();
        boolean result = validation.validateIfCityNameIsEmpty("Krakow");

        assertThat(result).isFalse();
    }

    @Test
    void shouldCheckIfTheFieldCityIsEmpty() {
        Validation validation = new Validation();
        boolean result = validation.validateIfCityNameIsEmpty("");

        assertThat(result).isTrue();
    }
}
