package edu.neoflex.vacation_pay_calculator.calculator.calculators;

import edu.neoflex.vacation_pay_calculator.calculator.VacationPayCalculator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;

public class VacationPayCalculatorWithHolidaysDecorator extends VacationPayCalculatorDecorator {

    /**
     * Официальные праздники
     */
    private static final Map<Integer, Integer[]> HOLIDAYS = Map.of(
            Calendar.JANUARY, new Integer[] {1, 2, 3, 4, 5, 6, 7, 8},
            Calendar.FEBRUARY, new Integer[] {23},
            Calendar.MARCH, new Integer[] {8},
            Calendar.MAY, new Integer[] {1, 9},
            Calendar.JUNE, new Integer[] {12},
            Calendar.NOVEMBER, new Integer[] {4}
    );

    private Calendar vacationDate;

    public VacationPayCalculatorWithHolidaysDecorator(VacationPayCalculator decoratedCalculator, Calendar vacationDate) {
        super(decoratedCalculator);
        this.vacationDate = vacationDate;
    }

    public VacationPayCalculatorWithHolidaysDecorator(VacationPayCalculator decoratedCalculator) {
        super(decoratedCalculator);
    }

    public void setVacationDate(Calendar vacationDate) {
        this.vacationDate = vacationDate;
    }

    @Override
    public BigDecimal calculate(BigDecimal salary, int vacationDays) {
        vacationDays -= holidaysCount(vacationDays);
        return decoratedCalculator.calculate(salary, vacationDays);
    }

    /**
     * Вычисление количества выходных и праздников, которые входят в период отпуска
     * @param vacationDays количество дней отпуска
     * @return количество праздников и выходных
     */
    private int holidaysCount(int vacationDays) {
        int holidaysCount = 0;

        //Цикл начинается с даты начала отпуска. На каждой итерации проверяется следующий день
        for (Calendar iterationDate = vacationDate; vacationDays > 0; vacationDays--, iterationDate.set(Calendar.DAY_OF_MONTH, iterationDate.get(Calendar.DAY_OF_MONTH) + 1)) {
            //Если день - суббота или воскресенье, количество выходных увеличивается на 1
            if (iterationDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || iterationDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                holidaysCount++;
            }

            //Если в месяце проверяемой даты нет праздников, переходим на следующую итерацию
            if (!HOLIDAYS.containsKey(iterationDate.get(Calendar.MONTH))) {
                continue;
            }

            //Если проверяемая дата - официальный праздник, количество выходных увеличивается на 1
            if (Arrays.stream(HOLIDAYS.get(iterationDate.get(Calendar.MONTH))).anyMatch(d -> d == iterationDate.get(Calendar.DAY_OF_MONTH))) {
                holidaysCount++;
            }
        }

        return holidaysCount;
    }
}
