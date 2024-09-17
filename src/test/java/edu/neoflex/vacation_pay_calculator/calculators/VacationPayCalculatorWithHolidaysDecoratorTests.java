package edu.neoflex.vacation_pay_calculator.calculators;

import edu.neoflex.vacation_pay_calculator.calculator.calculators.VacationPayCalculatorImpl;
import edu.neoflex.vacation_pay_calculator.calculator.calculators.VacationPayCalculatorWithHolidaysDecorator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Calendar;

@SpringBootTest
public class VacationPayCalculatorWithHolidaysDecoratorTests {

    VacationPayCalculatorWithHolidaysDecorator calculator;
    Calendar vacationDate;

    @BeforeEach
    public void initialize() {
        calculator = new VacationPayCalculatorWithHolidaysDecorator(new VacationPayCalculatorImpl());
        vacationDate = Calendar.getInstance();
    }

    @Test
    public void calculate() {
        vacationDate.set(2024, Calendar.SEPTEMBER, 17);
        calculator.setVacationDate(vacationDate);
        Assertions.assertEquals(calculator.calculate(BigDecimal.valueOf(29300.0), 10), BigDecimal.valueOf(8000.0));

        vacationDate.set(2024, Calendar.NOVEMBER, 1);
        calculator.setVacationDate(vacationDate);
        Assertions.assertEquals(calculator.calculate(BigDecimal.valueOf(29300.0), 10), BigDecimal.valueOf(5000.0));

        vacationDate.set(2024, Calendar.JANUARY, 1);
        calculator.setVacationDate(vacationDate);
        Assertions.assertEquals(calculator.calculate(BigDecimal.valueOf(29300.0), 8), BigDecimal.valueOf(0));
    }
}
