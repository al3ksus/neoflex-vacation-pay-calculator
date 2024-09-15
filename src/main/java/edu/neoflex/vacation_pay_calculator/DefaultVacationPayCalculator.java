package edu.neoflex.vacation_pay_calculator;

public class DefaultVacationPayCalculator implements VacationPayCalculator {
    @Override
    public double calculate(double salary, int countDays) {
        return salary / Constants.AVG_DAYS_IN_MONTH * countDays;
    }
}
