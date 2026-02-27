package com.example.unittesting;

public class FizzBuzz {
    public String convert(int number) {
        StringBuilder result = new StringBuilder();

        if (number % 3 == 0) {
            result.append("Fizz");
        }
        if (number % 5 == 0) {
            result.append("Buzz");
        }

        return result.isEmpty() ? Integer.toString(number) : result.toString();
    }
}
