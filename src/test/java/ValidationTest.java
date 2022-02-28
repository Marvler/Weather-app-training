
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidationTest {


    @Test
    public void shouldCheckIfTheCoordinatesAreValid() {
        Validation validation = new Validation();
        boolean result = validation.validateTheCoordinates("-52.21, 42.65");

        assertThat(result).isTrue();
    }

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

    @Test
    void shouldCheckIfTheFieldCityIsNotNull() {
        Validation validation = new Validation();
        boolean result = validation.validateIfCityNameIsEmpty("Krakow");

        assertThat(result).isNotNull();
    }

    @Test
    void shouldCheckIfTheFieldCountryNameIsNotEmpty() {
        Validation validation = new Validation();
        boolean result = validation.validateIfCountryNameIsEmpty("Poland");

        assertThat(result).isFalse();
    }

    @Test
    void shouldCheckIfTheFieldCountryNameIsEmpty() {
        Validation validation = new Validation();
        boolean result = validation.validateIfCountryNameIsEmpty("");

        assertThat(result).isTrue();
    }

    @Test
    void shouldCheckIfTheFieldCountryNameIsNotNull() {
        Validation validation = new Validation();
        boolean result = validation.validateIfCountryNameIsEmpty("Poland");

        assertThat(result).isNotNull();
    }

}
