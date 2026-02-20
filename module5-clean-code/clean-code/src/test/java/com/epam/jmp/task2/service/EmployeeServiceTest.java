package com.epam.jmp.task2.service;

import com.epam.jmp.task2.exception.InvalidEmployeeType;
import com.epam.jmp.task2.model.Money;
import com.epam.jmp.task2.model.SalariedEmployee;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeServiceTest {

    @Test
    void calculatePay_throwsWhenEmployeeIsNull() {
        EmployeeService service = new EmployeeService();
        InvalidEmployeeType ex = assertThrows(InvalidEmployeeType.class, () -> service.calculatePay(null));
        assertEquals("Employee is null", ex.getMessage());
    }

    @Test
    void calculateBonus_throwsWhenEmployeeIsNull() {
        EmployeeService service = new EmployeeService();

        InvalidEmployeeType ex = assertThrows(InvalidEmployeeType.class, () -> service.calculateBonus(null));
        assertEquals("Employee is null", ex.getMessage());
    }

    @Test
    void calculatePay_delegatesToEmployee() throws InvalidEmployeeType {
        EmployeeService service = new EmployeeService();
        SalariedEmployee employee = new SalariedEmployee("Alice", BigDecimal.valueOf(1000));

        Money pay = service.calculatePay(employee);

        assertEquals(new Money(BigDecimal.valueOf(1000), Currency.getInstance("USD")), pay);
    }
}

