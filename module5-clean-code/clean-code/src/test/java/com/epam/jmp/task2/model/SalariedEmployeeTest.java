package com.epam.jmp.task2.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SalariedEmployeeTest {

    @Test
    void calculatePay_returnsMonthlySalaryInUsd() {
        SalariedEmployee employee = new SalariedEmployee("Alice", BigDecimal.valueOf(1000));
        Money pay = employee.calculatePay();
        assertEquals(0, BigDecimal.valueOf(1000).compareTo(pay.amount()));
    }

    @Test
    void calculateBonus_returnsTenPercentOfMonthlySalaryInUsd() {
        SalariedEmployee employee = new SalariedEmployee("Alice", BigDecimal.valueOf(1000));
        Money bonus = employee.calculateBonus();
        assertEquals(0, BigDecimal.valueOf(100.0).compareTo(bonus.amount()));
    }
}

