
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.validators.Validation;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidationTest {

    private Validation validation;

    @BeforeEach
    void createValidation() {
        validation = new Validation();
    }

    @Test
    public void shouldCheckIfTheCoordinatesAreValid() {
        boolean result = validation.validateTheCoordinates("52N, 21E");

        assertThat(result).isTrue();
    }

    @Test
    public void shouldCheckIfTheCoordinatesAreNotValid() {
        boolean result = validation.validateTheCoordinates("52N21E");

        assertThat(result).isFalse();
    }

    @Test
    public void shouldCheckIfTheCoordinatesAreNotValidWithoutDirection() {
        boolean result = validation.validateTheCoordinates("52, 21");

        assertThat(result).isFalse();
    }

    @Test
    public void shouldCheckIfTheCoordinatesAreOutOfRange() {
        boolean result = validation.validateTheCoordinates("520N, 210E");

        assertThat(result).isFalse();
    }

    @Test
    void shouldCheckIfTheFieldCityIsNotEmpty() {
        boolean result = validation.validateIfCityNameIsEmpty("Krakow");

        assertThat(result).isFalse();
    }

    @Test
    void shouldCheckIfTheFieldCityIsEmpty() {
        boolean result = validation.validateIfCityNameIsEmpty("");

        assertThat(result).isTrue();
    }

    @Test
    void shouldCheckIfTheFieldCityIsNotNull() {
        boolean result = validation.validateIfCityNameIsEmpty("Krakow");

        assertThat(result).isNotNull();
    }

    @Test
    void shouldCheckIfTheFieldCountryNameIsNotEmpty() {
        boolean result = validation.validateIfCountryNameIsEmpty("Poland");

        assertThat(result).isFalse();
    }

    @Test
    void shouldCheckIfTheFieldCountryNameIsEmpty() {
        boolean result = validation.validateIfCountryNameIsEmpty("");

        assertThat(result).isTrue();
    }

    @Test
    void shouldCheckIfTheFieldCountryNameIsNotNull() {
        boolean result = validation.validateIfCountryNameIsEmpty("Poland");

        assertThat(result).isNotNull();
    }

    @Test
    void shouldCheckIfCityExists() {
        boolean result = validation.validateIfCityExists("Cracow");

        assertThat(result).isTrue();

    }

    @Test
    void shouldCheckIfDoesntCityExists() {
        boolean result = validation.validateIfCityExists("XYZ");

        assertThat(result).isFalse();
    }

    @Test
    void shouldCheckIfDateIsValid() {
        boolean result = Validation.isDateValid("2022-03-23");

        assertThat(result).isTrue();
    }

    @Test
    void shouldCheckIfDateIsNotValid() {
        boolean result = Validation.isDateValid("23-03-2022");

        assertThat(result).isFalse();
    }

    @Test
    void shouldCheckIfDateIsNotValidAtAll() {
        boolean result = Validation.isDateValid("34 maj 3100");

        assertThat(result).isFalse();
    }


}