package com.epam.jmp.task2.model;

import java.math.BigDecimal;
import java.util.Currency;

import static com.epam.jmp.task2.utils.Constants.DEFAULT_BONUS_HOURS;
import static com.epam.jmp.task2.utils.Constants.USD;

public class HourlyEmployee extends Employee {

    private final BigDecimal hourlyRate;
    private final int hoursWorked;

    public HourlyEmployee(String name, BigDecimal hourlyRate, int hoursWorked) {
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public Money calculatePay() {
        BigDecimal totalPay = hourlyRate.multiply(BigDecimal.valueOf(hoursWorked));
        return new Money(totalPay, Currency.getInstance(USD));
    }

    @Override
    public Money calculateBonus() {
        BigDecimal bonus = hourlyRate.multiply(DEFAULT_BONUS_HOURS);
        return new Money(bonus, Currency.getInstance(USD));
    }
}


