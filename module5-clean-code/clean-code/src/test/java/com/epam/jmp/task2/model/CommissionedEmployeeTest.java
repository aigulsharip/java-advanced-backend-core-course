package com.epam.jmp.task2.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommissionedEmployeeTest {

    @Test
    void calculatePay_isBasePlusCommissionInUsd() {
        CommissionedEmployee employee = new CommissionedEmployee(
                "Carol",
                BigDecimal.valueOf(500),
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(0.07)
        );

        Money pay = employee.calculatePay();
        assertEquals(Currency.getInstance("USD"), pay.currency());
        assertEquals(0, BigDecimal.valueOf(570.0).compareTo(pay.amount()));
    }

    @Test
    void calculateBonus_isFiftyPercentOfCommissionInUsd() {
        CommissionedEmployee employee = new CommissionedEmployee(
                "Carol",
                BigDecimal.valueOf(500),
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(0.07)
        );

        Money bonus = employee.calculateBonus();
        assertEquals(0, BigDecimal.valueOf(35.0).compareTo(bonus.amount()));
    }
}
