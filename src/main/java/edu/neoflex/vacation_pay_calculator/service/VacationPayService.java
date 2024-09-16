package edu.neoflex.vacation_pay_calculator.service;

import edu.neoflex.vacation_pay_calculator.calculator.VacationPayCalculator;
import edu.neoflex.vacation_pay_calculator.calculator.VacationPayCalculatorFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.Instant;

@Service
public class VacationPayService {

    public BigDecimal calculate(BigDecimal salary, Integer vacationDays, String vacationDate) throws ParseException {
        final VacationPayCalculator calculator = VacationPayCalculatorFactory.getCalculator(vacationDate);
        return calculator.calculate(salary, vacationDays);
    }
}