package edu.neoflex.vacation_pay_calculator.calculator;

import java.math.BigDecimal;

public interface VacationPayCalculator {
    /**
     * Расчет отпускных по стандартной формуле: среднедневная зарплата * количество дней отпуска
     * @param salary Среднемесячная зарплата
     * @param vacationDays Количество дней отпуска
     * @return Отпускные
     */
    BigDecimal calculate(BigDecimal salary, int vacationDays);
}
