
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidationTest {


    @Test
    public void shouldCheckIfTheCoordinatesAreValid() {
        Validation validation = new Validation();
        String result = validation.validateTheCoordinates("-52.21, 42.65");

        assertThat(result).isEqualTo("-52.21, 42.65");
    }

    @Test
    void shouldCheckIfTheFieldCityIsNotEmpty() {
        Validation validation = new Validation();
        String result = validation.validateIfCityNameIsEmpty("Krakow");

        assertThat(result).isEqualTo("Krakow");
    }

    @Test
    @Disabled
    void shouldCheckIfTheFieldCityIsEmpty() {
        Validation validation = new Validation();
        String result = validation.validateIfCityNameIsEmpty("");

        assertThat(result).isNotEmpty();
    }

    @Test
    void shouldCheckIfTheFieldCityIsNotNull() {
        Validation validation = new Validation();
        String result = validation.validateIfCityNameIsEmpty("Krakow");

        assertThat(result).isNotNull();
    }

    @Test
    void shouldCheckIfTheFieldCountryNameIsNotEmpty() {
        Validation validation = new Validation();
        String result = validation.validateIfCountryNameIsEmpty("Poland");

        assertThat(result).isNotEmpty();
    }

    @Test
    @Disabled
    void shouldCheckIfTheFieldCountryNameIsEmpty() {
        Validation validation = new Validation();
        String result = validation.validateIfCountryNameIsEmpty("");

        assertThat(result).isEmpty();
    }

    @Test
    void shouldCheckIfTheFieldCountryNameIsNotNull() {
        Validation validation = new Validation();
        String result = validation.validateIfCountryNameIsEmpty("Poland");

        assertThat(result).isEqualTo("Poland");
    }

}
