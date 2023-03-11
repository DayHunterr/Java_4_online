package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CalendarController {
    private CustomCalendar calendar = new CustomCalendar();
    private String pattern = "yyyy dd MMMM HH год, mm хв, ss сек, SSS мілісекунд";

    public void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select your options");
        String select;
        menu();
        while ((select = reader.readLine()) != null) {
            crud(reader, select);
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("Select an option:");
        System.out.println("1. Print current date and time");
        System.out.println("2. Set date and time");
        System.out.println("3. Add time interval");
        System.out.println("4. Subtract time interval");
        System.out.println("5. Difference between date and time");
        System.out.println("6. Quit");
        System.out.println();
    }

    private void crud(BufferedReader reader, String select) throws IOException {
        switch (select) {
            case "1" -> print();
            case "2" -> set(reader);
            case "3" -> add(reader);
            case "4" -> subtract(reader);
            case "5" -> difference(reader);
            case "6" -> exit();
        }
        menu();
    }

    private void print(){
        System.out.println("Current date and time: " + calendar.format(pattern));
    }

    private void set(BufferedReader reader) throws IOException {
        System.out.println("Select an option:");
        System.out.println("1. Set years and months");
        System.out.println("2. Set years and months and days and hours and minutes");
        System.out.println("3. Set years and months and days and hours and minutes and seconds and milliseconds");
        System.out.println("4. Set date");
        switch (reader.readLine()) {
            case "1" -> {
                System.out.println("Enter number of years to set:");
                int years = Integer.parseInt(reader.readLine());
                System.out.println("Enter number of months to set:");
                int months = Integer.parseInt(reader.readLine());
                calendar.set(years, months);
            }
            case "2" -> {
                System.out.println("Enter number of years to set:");
                int years = Integer.parseInt(reader.readLine());
                System.out.println("Enter number of months to set:");
                int months = Integer.parseInt(reader.readLine());
                System.out.println("Enter number of days to set:");
                int days = Integer.parseInt(reader.readLine());
                System.out.println("Enter number of hours to set:");
                int hours = Integer.parseInt(reader.readLine());
                System.out.println("Enter number of minutes to set:");
                int minutes = Integer.parseInt(reader.readLine());
                calendar.set(years,months,days,hours,minutes);
            }
            case "3" -> {
                System.out.println("Enter number of years to set:");
                int years = Integer.parseInt(reader.readLine());
                System.out.println("Enter number of months to set:");
                int months = Integer.parseInt(reader.readLine());
                System.out.println("Enter number of days to set:");
                int days = Integer.parseInt(reader.readLine());
                System.out.println("Enter number of hours to set:");
                int hours = Integer.parseInt(reader.readLine());
                System.out.println("Enter number of minutes to set:");
                int minutes = Integer.parseInt(reader.readLine());
                System.out.println("Enter number of seconds to set:");
                int seconds = Integer.parseInt(reader.readLine());
                System.out.println("Enter number of milliseconds to set:");
                int milliseconds = Integer.parseInt(reader.readLine());
                calendar.set(years,months,days,hours,minutes,seconds,milliseconds);
            }
            case "4" -> {
                System.out.println("Enter date and time in yyyy-MM-dd HH:mm:ss format:");
                String format = reader.readLine();
                calendar.set(format);
            }
            default -> System.out.println("Invalid option");
        }
        System.out.println("New date and time: " + calendar.format(pattern));
    }

    private void add(BufferedReader reader) throws IOException {
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Add years");
            System.out.println("2. Add months");
            System.out.println("3. Add days");
            System.out.println("4. Add hours");
            System.out.println("5. Add minutes");
            System.out.println("6. Add seconds");
            System.out.println("7. Add milliseconds");
            System.out.println("8. Add date");
            System.out.println("9. Exit");

            switch (reader.readLine()) {
                case "1" -> {
                    System.out.println("Enter number of years to add:");
                    int years = Integer.parseInt(reader.readLine());
                    calendar.addYears(years);
                }
                case "2" -> {
                    System.out.println("Enter number of months to add:");
                    int months = Integer.parseInt(reader.readLine());
                    calendar.addMonths(months);
                }
                case "3" -> {
                    System.out.println("Enter number of days to add:");
                    int days = Integer.parseInt(reader.readLine());
                    calendar.addDays(days);
                }
                case "4" -> {
                    System.out.println("Enter number of hours to add:");
                    int hours = Integer.parseInt(reader.readLine());
                    calendar.addHours(hours);
                }
                case "5" -> {
                    System.out.println("Enter number of minutes to add:");
                    int minutes = Integer.parseInt(reader.readLine());
                    calendar.addMinutes(minutes);
                }
                case "6" -> {
                    System.out.println("Enter number of seconds to add:");
                    int seconds = Integer.parseInt(reader.readLine());
                    calendar.addSeconds(seconds);
                }
                case "7" -> {
                    System.out.println("Enter number of milliseconds to add:");
                    int milliseconds = Integer.parseInt(reader.readLine());
                    calendar.addMilliseconds(milliseconds);
                }
                case "8" -> {
                    System.out.println("Enter date and time in yyyy-MM-dd HH:mm:ss format:");
                    String format = reader.readLine();
                    calendar.addDate(format);
                }
                case "9" -> {
                    return;
                }
                default -> System.out.println("Invalid option");
            }

            System.out.println("New date and time: " + calendar.format(pattern));
        }
    }

    private void subtract(BufferedReader reader) throws IOException {
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Subtract years");
            System.out.println("2. Subtract months");
            System.out.println("3. Subtract days");
            System.out.println("4. Subtract hours");
            System.out.println("5. Subtract minutes");
            System.out.println("6. Subtract seconds");
            System.out.println("7. Subtract milliseconds");
            System.out.println("8. Subtract date");
            System.out.println("9. Exit");
            try {
                switch (reader.readLine()) {
                    case "1" -> {
                        System.out.println("Enter number of years to subtract:");
                        int years = Integer.parseInt(reader.readLine());
                        calendar.minusYears(years);
                    }
                    case "2" -> {
                        System.out.println("Enter number of months to subtract:");
                        int months = Integer.parseInt(reader.readLine());
                        calendar.minusMonths(months);
                    }
                    case "3" -> {
                        System.out.println("Enter number of days to subtract:");
                        int days = Integer.parseInt(reader.readLine());
                        calendar.minusDays(days);
                    }
                    case "4" -> {
                        System.out.println("Enter number of hours to subtract:");
                        int hours = Integer.parseInt(reader.readLine());
                        calendar.minusHours(hours);
                    }
                    case "5" -> {
                        System.out.println("Enter number of minutes to subtract:");
                        int minutes = Integer.parseInt(reader.readLine());
                        calendar.minusMinutes(minutes);
                    }
                    case "6" -> {
                        System.out.println("Enter number of seconds to subtract:");
                        int seconds = Integer.parseInt(reader.readLine());
                        calendar.minusSeconds(seconds);
                    }
                    case "7" -> {
                        System.out.println("Enter number of milliseconds to subtract:");
                        int milliseconds = Integer.parseInt(reader.readLine());
                        calendar.minusMilliseconds(milliseconds);
                    }
                    case "8" -> {
                        System.out.println("Enter date and time in yyyy-MM-dd HH:mm:ss format:");
                        String format = reader.readLine();
                        calendar.minusDate(format);
                    }
                    case "9" -> {
                        return;
                    }
                    default -> System.out.println("Invalid option");
                }
            }catch (Exception e){
                System.out.println("Invalid option");
            }

            System.out.println("New date and time: " + calendar.format(pattern));
        }
    }

    private void difference(BufferedReader reader) throws IOException {
        System.out.println("Enter date and time in yyyy-MM-dd HH:mm:ss format:");
        CustomCalendar second = new CustomCalendar(reader.readLine());
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Difference in years");
            System.out.println("2. Difference in months");
            System.out.println("3. Difference in days");
            System.out.println("4. Difference in hours");
            System.out.println("5. Difference in minutes");
            System.out.println("6. Difference in seconds");
            System.out.println("7. Difference in milliseconds");
            System.out.println("8. Exit");

            switch (reader.readLine()) {
                case "1" -> System.out.println("Difference: " + calendar.differenceInYears(calendar, second));
                case "2" -> System.out.println("Difference: " + calendar.differenceInMonths(calendar, second));
                case "3" -> System.out.println("Difference: " + calendar.differenceInDays(calendar, second));
                case "4" -> System.out.println("Difference: " + calendar.differenceInHours(calendar, second));
                case "5" -> System.out.println("Difference: " + calendar.differenceInMinutes(calendar, second));
                case "6" -> System.out.println("Difference: " + calendar.differenceInSeconds(calendar, second));
                case "7" -> System.out.println("Difference: " + calendar.differenceInMilliseconds(calendar, second));
                case "8" -> {
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }
    private void exit(){
        System.exit(0);
    }

    private void stop() {
        System.exit(0);
    }
}