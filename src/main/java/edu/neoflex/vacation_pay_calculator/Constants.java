package edu.neoflex.vacation_pay_calculator;

import java.util.Calendar;
import java.util.Map;

public class Constants {

    public static final double AVG_DAYS_IN_MONTH = 29.3;

    public static final Map<Integer, Integer[]> HOLIDAYS = Map.of(
            Calendar.JANUARY, new Integer[] {1, 2, 3, 4, 5, 6, 7, 8},
            Calendar.FEBRUARY, new Integer[] {23},
            Calendar.MARCH, new Integer[] {8},
            Calendar.MAY, new Integer[] {1, 9},
            Calendar.JUNE, new Integer[] {12},
            Calendar.NOVEMBER, new Integer[] {4}
    );
}
