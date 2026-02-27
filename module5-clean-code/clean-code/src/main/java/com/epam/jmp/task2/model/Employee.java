package com.epam.jmp.task2.model;

public abstract class Employee {
    protected String name;

    public abstract Money calculatePay();

    public abstract Money calculateBonus();
}

