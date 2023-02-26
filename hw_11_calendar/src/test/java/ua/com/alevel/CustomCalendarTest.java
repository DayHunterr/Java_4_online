package ua.com.alevel;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomCalendarTest {

    @Test
    @Order(1)
    public void testNow() {
        CustomCalendar calendar = CustomCalendar.now();
        assertNotNull(calendar);
        long currentTimeMillis = System.currentTimeMillis();
        assertTrue(currentTimeMillis - calendar.getTimeMillis() < 100);
    }

    @Test
    @Order(2)
    public void testSet() {
        CustomCalendar calendar = CustomCalendar.now();
        calendar.set(2022, 2);
        assertEquals(1643673600000L, calendar.getTimeMillis());
        calendar.set(2022, 2, 10, 8, 30);
        assertEquals(1644481800000L, calendar.getTimeMillis());
        calendar.set(2022, 2, 10, 8, 30, 15, 500);
        assertEquals(1644481815500L, calendar.getTimeMillis());
        calendar.set("2022-03-10 08:30:15 500");
        assertEquals(1646901015500L, calendar.getTimeMillis());
    }

    @Test
    @Order(3)
    public void testAdd() {
        CustomCalendar calendar = CustomCalendar.now();
        CustomCalendar calendarToAdd = new CustomCalendar("2023-03-10 08:30:15 500");
        long expectedDiff = calendarToAdd.getTimeMillis() + calendar.getTimeMillis();
        calendar.addDate(calendarToAdd);
        assertEquals(expectedDiff, calendar.getTimeMillis());
        calendar = CustomCalendar.now();
        long expectedDiff1 = 1678437015500L + calendar.getTimeMillis();
        calendar.addDate("2023-03-10 08:30:15 500");
        assertEquals(expectedDiff1, calendar.getTimeMillis());
        calendar = new CustomCalendar("2023-02-10 08:30:15 500");
        calendar.addYears(2);
        assertEquals(1739176215500L, calendar.getTimeMillis());
        calendar.addMonths(2);
        assertEquals(1744273815500L, calendar.getTimeMillis());
        calendar.addDays(10);
        assertEquals(1745137815500L, calendar.getTimeMillis());
        calendar.addHours(10);
        assertEquals(1745173815500L, calendar.getTimeMillis());
        calendar.addMinutes(10);
        assertEquals(1745174415500L, calendar.getTimeMillis());
        calendar.addSeconds(10);
        assertEquals(1745174425500L, calendar.getTimeMillis());
        calendar.addMilliseconds(50);
        assertEquals(1745174425550L, calendar.getTimeMillis());
    }

    @Test
    @Order(4)
    public void testMinus() {
        CustomCalendar calendar = new CustomCalendar("2023-03-10 08:30:15 500");
        CustomCalendar calendarToMinus = new CustomCalendar("2023-02-10 08:30:15 500");
        long expectedDiff = calendar.getTimeMillis() - calendarToMinus.getTimeMillis();
        calendar.minusDate(calendarToMinus);
        assertEquals(expectedDiff, calendar.getTimeMillis());
        calendar = new CustomCalendar("2023-03-10 08:30:15");
        calendar.minusYears(2);
        assertEquals(1615365015000L, calendar.getTimeMillis());
        calendar.minusMonths(2);
        assertEquals(1610267415000L, calendar.getTimeMillis());
        calendar.minusDays(10);
        assertEquals(1609403415000L, calendar.getTimeMillis());
        calendar.minusHours(10);
        assertEquals(1609367415000L, calendar.getTimeMillis());
        calendar.minusMinutes(10);
        assertEquals(1609366815000L, calendar.getTimeMillis());
        calendar.minusSeconds(10);
        assertEquals(1609366805000L, calendar.getTimeMillis());
        calendar.minusMilliseconds(50);
        assertEquals(1609366804950L, calendar.getTimeMillis());
    }

    @Test
    @Order(5)
    public void testDifferenceIn() {
        CustomCalendar calendar1 = new CustomCalendar("2020-03-10 08:30:15 500");
        CustomCalendar calendar2 = new CustomCalendar("2020-02-10 08:30:15 500");
        assertEquals(calendar1.differenceInYears(calendar1, calendar2), 0);
        assertEquals(calendar1.differenceInMonths(calendar1, calendar2), 1);
        assertEquals(calendar1.differenceInDays(calendar1, calendar2), 29);
        assertEquals(calendar1.differenceInHours(calendar1, calendar2), 696);
        assertEquals(calendar1.differenceInMinutes(calendar1, calendar2), 41760);
        assertEquals(calendar1.differenceInSeconds(calendar1, calendar2), 2505600);
        assertEquals(calendar1.differenceInMilliseconds(calendar1, calendar2), 2505600000L);
        calendar1 = new CustomCalendar("2021-02-10");
        calendar2 = new CustomCalendar("2020-02-10");
        assertEquals(calendar1.differenceInYears(calendar1, calendar2), 1);
        assertEquals(calendar1.differenceInMonths(calendar1, calendar2), 12);
        assertEquals(calendar1.differenceInDays(calendar1, calendar2), 366);
        assertEquals(calendar1.differenceInHours(calendar1, calendar2), 8784);
        assertEquals(calendar1.differenceInMinutes(calendar1, calendar2), 527040);
        assertEquals(calendar1.differenceInSeconds(calendar1, calendar2), 31622400);
        assertEquals(calendar1.differenceInMilliseconds(calendar1, calendar2), 31622400000L);
    }
}