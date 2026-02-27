package com.epam.jmp.task2.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HourlyEmployeeTest {

    @Test
    void calculatePay_multipliesHoursByRateInUsd() {
        HourlyEmployee employee = new HourlyEmployee("Bob", BigDecimal.valueOf(20), 10);
        Money pay = employee.calculatePay();
        assertEquals(0, BigDecimal.valueOf(200).compareTo(pay.amount()));
    }

    @Test
    void calculateBonus_isHourlyRateTimesDefaultBonusHoursInUsd() {
        HourlyEmployee employee = new HourlyEmployee("Bob", BigDecimal.valueOf(20), 10);
        Money bonus = employee.calculateBonus();
        assertEquals(0, BigDecimal.valueOf(160).compareTo(bonus.amount()));
    }
}

