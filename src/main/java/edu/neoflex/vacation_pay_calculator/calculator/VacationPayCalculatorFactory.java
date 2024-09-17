package edu.neoflex.vacation_pay_calculator.calculator;

import edu.neoflex.vacation_pay_calculator.calculator.calculators.VacationPayCalculatorImpl;
import edu.neoflex.vacation_pay_calculator.calculator.calculators.VacationPayCalculatorWithHolidaysDecorator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class VacationPayCalculatorFactory {

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    public static VacationPayCalculator getCalculator(String vacationDate) throws ParseException {
        if (vacationDate == null) {
            return new VacationPayCalculatorImpl();
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new SimpleDateFormat(DATE_PATTERN).parse(vacationDate));
            return new VacationPayCalculatorWithHolidaysDecorator(new VacationPayCalculatorImpl(), calendar);
        }
    }
}
