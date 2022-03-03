
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import services.validators.Validation;

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

        assertThat(result).isTrue();
    }
    
    // Comment

    @Test
    @Disabled
    void shouldCheckIfTheFieldCityIsEmpty() {
        Validation validation = new Validation();
        boolean result = validation.validateIfCityNameIsEmpty("");

        assertThat(result).isFalse();
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

        assertThat(result).isTrue();
    }

    @Test
    @Disabled
    void shouldCheckIfTheFieldCountryNameIsEmpty() {
        Validation validation = new Validation();
        boolean result = validation.validateIfCountryNameIsEmpty("");

        assertThat(result).isFalse();
    }

    @Test
    void shouldCheckIfTheFieldCountryNameIsNotNull() {
        Validation validation = new Validation();
        boolean result = validation.validateIfCountryNameIsEmpty("Poland");

        assertThat(result).isTrue();
    }

}
