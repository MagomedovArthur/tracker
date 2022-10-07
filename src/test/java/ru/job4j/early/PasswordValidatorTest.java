package ru.job4j.early;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.early.PasswordValidator.validate;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PasswordValidatorTest {

    @Test
    void whenEmptyPassword() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    validate("");
                }
        );
        assertThat(exception.getMessage()).isEqualTo("Password length must be between 8 and 32 characters!");
    }

    @Test
    void whenPasswordDoesNotContainLowercaseCharacters() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    validate("HH545.#2");
                }
        );
        assertThat(exception.getMessage()).isEqualTo("Password must contain lowercase character.");
    }

    @Test
    void whenPasswordDoesNotContainUppercaseCharacters() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    validate("12312##hhassadas");
                }
        );
        assertThat(exception.getMessage()).isEqualTo("Password must contain uppercase character.");
    }

    @Test
    void whenPasswordDoesNotContainNumbers() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    validate("QUWYQUW##hhassadas");
                }
        );
        assertThat(exception.getMessage()).isEqualTo("Password must contain at least one number");
    }

    @Test
    void whenPasswordDoesNotContainSpecialCharacters() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    validate("JSHJAasjkdhs12736182");
                }
        );
        assertThat(exception.getMessage()).isEqualTo("Password must contain at least one special character");
    }

    @Test
    void whenPasswordIsTooEasy() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    validate("qWErty");
                }
        );
        assertThat(exception.getMessage()).isEqualTo("Password is too easy, please enter another one");
    }

    @Test
    void whenPasswordIs12345() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    validate("12345");
                }
        );
        assertThat(exception.getMessage()).isEqualTo("Password is too easy, please enter another one");
    }
}