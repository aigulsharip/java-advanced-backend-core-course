package com.epam.jmp.task2.model;

import java.math.BigDecimal;
import java.util.Currency;

import static com.epam.jmp.task2.utils.Constants.DEFAULT_SALARY_BONUS_RATE;
import static com.epam.jmp.task2.utils.Constants.USD;

public class SalariedEmployee extends Employee {

    private final BigDecimal monthlySalary;

    public SalariedEmployee(String name, BigDecimal monthlySalary) {
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    @Override
    public Money calculatePay() {
        return new Money(monthlySalary, Currency.getInstance(USD));
    }

    @Override
    public Money calculateBonus() {
        BigDecimal bonus = monthlySalary.multiply(DEFAULT_SALARY_BONUS_RATE);
        return new Money(bonus, Currency.getInstance(USD));
    }
}
