package com.epam.jmp.task2.service;

import com.epam.jmp.task2.exception.InvalidEmployeeType;
import com.epam.jmp.task2.model.Employee;
import com.epam.jmp.task2.model.Money;

public class EmployeeService {

    public Money calculatePay(Employee employee) throws InvalidEmployeeType {
        if (employee != null) {
            return employee.calculatePay();
        } else {
            throw new InvalidEmployeeType("Employee is null");
        }
    }

    public Money calculateBonus(Employee employee) throws InvalidEmployeeType {
        if (employee != null) {
            return employee.calculateBonus();
        } else {
            throw new InvalidEmployeeType("Employee is null");
        }
    }
}
