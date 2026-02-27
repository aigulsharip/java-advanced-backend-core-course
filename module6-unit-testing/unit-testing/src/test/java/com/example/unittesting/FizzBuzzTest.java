package com.example.unittesting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzBuzzTest {
    private final FizzBuzz fizzBuzz = new FizzBuzz();

    @Test
    void shouldReturnFizzWhenDivisibleByThree() {
        assertEquals("Fizz", fizzBuzz.convert(3));
    }

    @Test
    void shouldReturnBuzzWhenDivisibleByFive() {
        assertEquals("Buzz", fizzBuzz.convert(5));
    }

    @Test
    void shouldReturnFizzBuzzWhenDivisibleByFifteen() {
        assertEquals("FizzBuzz", fizzBuzz.convert(15));
    }

    @Test
    void shouldReturnNumberAsStringWhenNotDivisibleByThreeOrFive() {
        assertEquals("2", fizzBuzz.convert(2));
    }
}
