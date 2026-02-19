package com.epam.jmp.task2;

import java.util.Objects;

public final class CommissionedEmployee extends Employee {
    private final Money basePay;
    private final Money commission;

    public CommissionedEmployee(String name, Money basePay, Money commission) {
        super(name);
        this.basePay = Objects.requireNonNull(basePay, "basePay");
        this.commission = Objects.requireNonNull(commission, "commission");
    }

    @Override
    public Money calculatePay() {
        return basePay.plus(commission);
    }

    @Override
    public Money calculateBonus() {
        // Simple sample rule: 10% of commission.
        return new Money(commission.amount().multiply(java.math.BigDecimal.valueOf(0.10)));
    }
}

