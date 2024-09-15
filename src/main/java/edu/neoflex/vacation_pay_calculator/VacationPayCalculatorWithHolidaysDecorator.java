package edu.neoflex.vacation_pay_calculator;

import java.util.Arrays;
import java.util.Calendar;

public class VacationPayCalculatorWithHolidaysDecorator extends VacationPayCalculatorDecorator {

    Calendar startDate;

    public VacationPayCalculatorWithHolidaysDecorator(VacationPayCalculator decoratedCalculator, Calendar startDate) {
        super(decoratedCalculator);
        this.startDate = startDate;
    }

    @Override
    public double calculate(double salary, int countDays) {
        countDays -= holidaysCount(countDays);
        return decoratedCalculator.calculate(salary, countDays);
    }

    private int holidaysCount(int countDays) {
        int holidaysCount = 0;

        for (Calendar i = startDate; countDays > 0; countDays--, i.set(Calendar.DAY_OF_MONTH, i.get(Calendar.DAY_OF_MONTH) + 1)) {
            if (i.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || i.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                holidaysCount++;
            }

            if (!Constants.HOLIDAYS.containsKey(i.get(Calendar.MONTH))) {
                continue;
            }

            if (Arrays.stream(Constants.HOLIDAYS.get(i.get(Calendar.MONTH))).anyMatch(d -> d == i.get(Calendar.DAY_OF_MONTH))) {
                holidaysCount++;
            }
        }

        return holidaysCount;
    }
}
