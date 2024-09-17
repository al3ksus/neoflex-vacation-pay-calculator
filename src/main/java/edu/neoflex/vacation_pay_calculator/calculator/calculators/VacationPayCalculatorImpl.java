package edu.neoflex.vacation_pay_calculator.calculator.calculators;

import edu.neoflex.vacation_pay_calculator.calculator.VacationPayCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VacationPayCalculatorImpl implements VacationPayCalculator {

    /**
     * Среднее количество дней в месяце
     */
    private static final BigDecimal AVG_DAYS_IN_MONTH = BigDecimal.valueOf(29.3);

    @Override
    public BigDecimal calculate(BigDecimal salary, int vacationDays) {
        final BigDecimal calculatedValue = salary.divide(AVG_DAYS_IN_MONTH, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(vacationDays));
        if (calculatedValue.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO;
        } else {
            return calculatedValue;
        }
    }
}
