package com.example.unittesting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeapYearCalculatorTest {
    private final LeapYearCalculator calculator = new LeapYearCalculator();

    @Test
    void shouldReturnTrueWhenDivisibleByFour() {
        assertTrue(calculator.isLeapYear(1996));
    }

    @Test
    void shouldReturnFalseWhenNotDivisibleByFour() {
        assertFalse(calculator.isLeapYear(2017));
    }

    @Test
    void shouldReturnFalseWhenDivisibleBy100ButNot400() {
        assertFalse(calculator.isLeapYear(1900));
    }

    @Test
    void shouldReturnTrueWhenDivisibleBy400() {
        assertTrue(calculator.isLeapYear(2000));
    }
}
