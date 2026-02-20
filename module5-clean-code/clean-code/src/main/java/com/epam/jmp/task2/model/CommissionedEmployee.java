package com.epam.jmp.task2.model;

import java.math.BigDecimal;
import java.util.Currency;

import static com.epam.jmp.task2.utils.Constants.DEFAULT_COMMISSION_BONUS_RATE;
import static com.epam.jmp.task2.utils.Constants.USD;

public class CommissionedEmployee extends Employee {

    private final BigDecimal basePay;
    private final BigDecimal salesAmount;
    private final BigDecimal commissionRate;

    public CommissionedEmployee(String name, BigDecimal basePay, BigDecimal salesAmount, BigDecimal commissionRate) {
        this.name = name;
        this.basePay = basePay;
        this.salesAmount = salesAmount;
        this.commissionRate = commissionRate;
    }

    @Override
    public Money calculatePay() {
        BigDecimal commission = salesAmount.multiply(commissionRate);
        BigDecimal totalPay = basePay.add(commission);
        return new Money(totalPay, Currency.getInstance(USD));
    }

    @Override
    public Money calculateBonus() {
        BigDecimal commission = salesAmount.multiply(commissionRate);
        BigDecimal bonus = commission.multiply(DEFAULT_COMMISSION_BONUS_RATE);
        return new Money(bonus, Currency.getInstance(USD));
    }
}
