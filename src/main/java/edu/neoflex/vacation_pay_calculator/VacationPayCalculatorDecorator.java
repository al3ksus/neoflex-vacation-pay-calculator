package edu.neoflex.vacation_pay_calculator;

public abstract class VacationPayCalculatorDecorator implements VacationPayCalculator {

    VacationPayCalculator decoratedCalculator;

    public VacationPayCalculatorDecorator(VacationPayCalculator decoratedCalculator) {
        this.decoratedCalculator = decoratedCalculator;
    }

    @Override
    public double calculate(double salary, int countDays) {
        return decoratedCalculator.calculate(salary, countDays);
    }
}
