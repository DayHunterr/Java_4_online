package ua.com.alevel;

import java.io.IOException;
import java.text.ParseException;

public class CalendarMain {
    public static void main(String[] args) throws ParseException, IOException {

        CalendarController calendarController = new CalendarController();
        calendarController.start();

    }
}
