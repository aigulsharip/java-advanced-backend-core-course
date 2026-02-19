package com.epam.jmp.task2;

public abstract class Employee {
    protected String name;

    protected static final String USD = "USD";

    // Example bonus constants (adjust to your business rules)
    protected static final BigDecimal DEFAULT_BONUS_HOURS = BigDecimal.valueOf(8);     // e.g., 8h bonus for hourly
    protected static final BigDecimal DEFAULT_SALARY_BONUS_RATE = BigDecimal.valueOf(0.10); // 10% of salary
    protected static final BigDecimal DEFAULT_COMMISSION_BONUS_RATE = BigDecimal.valueOf(0.50); // 50% of commission




    public abstract Money calculatePay();

    public abstract Money calculateBonus();
}

