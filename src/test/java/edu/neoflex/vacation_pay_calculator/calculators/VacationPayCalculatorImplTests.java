package edu.neoflex.vacation_pay_calculator.calculators;

import edu.neoflex.vacation_pay_calculator.calculator.VacationPayCalculator;
import edu.neoflex.vacation_pay_calculator.calculator.calculators.VacationPayCalculatorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SpringBootTest
public class VacationPayCalculatorImplTests {

    @Test
    public void calculateTest() {
        VacationPayCalculator calculator = new VacationPayCalculatorImpl();
        Assertions.assertEquals(calculator.calculate(BigDecimal.valueOf(29300.0), 10), BigDecimal.valueOf(10000.0));
        Assertions.assertEquals(calculator.calculate(BigDecimal.valueOf(100000.0), 28).setScale(0, RoundingMode.HALF_UP), BigDecimal.valueOf(95564));
    }
}
