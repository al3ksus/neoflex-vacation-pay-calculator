package edu.neoflex.vacation_pay_calculator;

import edu.neoflex.vacation_pay_calculator.service.VacationPayService;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.logging.Logger;

@RestController
public class VacationPayController {

    private static final Logger log = Logger.getGlobal();

    private final VacationPayService vacationPayService;

    public VacationPayController(VacationPayService vacationPayService) {
        this.vacationPayService = vacationPayService;
    }

    @GetMapping
    @RequestMapping("/calculate")
    public ResponseEntity<?> calculate(
            @RequestParam("salary")
            @DecimalMin(value = "0", message = "Salary cannot be less than 0")
            BigDecimal salary,

            @RequestParam("vacationDays")
            @Min(value = 0, message = "Vacation days cannot be less than 0")
            @Max(value = 28, message = "Vacation days cannot be more than 28")
            int vacationDays,

            @RequestParam(value = "vacationDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            String vacationDate
    ) {
        log.info("Request for pay calculation: salary=" + salary +
                ", vacationDays=" + vacationDays +
                ", vacationDate=" + vacationDate);
        try {
            final BigDecimal calculated = vacationPayService.calculate(salary, vacationDays, vacationDate);
            return new ResponseEntity<>(calculated, HttpStatus.OK);
        } catch (Exception e) {
            log.warning(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
