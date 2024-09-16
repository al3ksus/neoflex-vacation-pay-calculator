package edu.neoflex.vacation_pay_calculator.calculator.calculators;

import edu.neoflex.vacation_pay_calculator.calculator.VacationPayCalculator;

import java.math.BigDecimal;

public abstract class VacationPayCalculatorDecorator implements VacationPayCalculator {

    VacationPayCalculator decoratedCalculator;

    public VacationPayCalculatorDecorator(VacationPayCalculator decoratedCalculator) {
        this.decoratedCalculator = decoratedCalculator;
    }

    @Override
    public BigDecimal calculate(BigDecimal salary, int vacationDays) {
        return decoratedCalculator.calculate(salary, vacationDays);
    }
}
