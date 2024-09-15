package edu.neoflex.vacation_pay_calculator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
public class VacationPayController {

    VacationPayCalculator vacationPayCalculator;

    @GetMapping
    @RequestMapping("/calculate")
    public ResponseEntity<Double> calculate(double salary, int countDays, String startDate) throws HttpClientErrorException.BadRequest {
        if (startDate == null) {
            vacationPayCalculator = new DefaultVacationPayCalculator();
            return new ResponseEntity<>(vacationPayCalculator.calculate(salary, countDays), HttpStatus.OK);
        }

        try {
            Date date = new SimpleDateFormat("dd.MM.yyyy").parse(startDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            vacationPayCalculator = new VacationPayCalculatorWithHolidaysDecorator(new DefaultVacationPayCalculator(), calendar);
        } catch (ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(vacationPayCalculator.calculate(salary, countDays), HttpStatus.OK);
    }
}
