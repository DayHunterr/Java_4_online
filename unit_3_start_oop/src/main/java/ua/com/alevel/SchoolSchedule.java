package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;

public class SchoolSchedule {

    public static String schedule(BufferedReader reader) throws IOException {

        int LessonNumber;
        int Hours;
        int LessonMinutes;

        for (;;) {
            try {
                System.out.print("Enter Lesson Number which ending you want to know(from 1 till 10): ");
                LessonNumber = Integer.parseInt(reader.readLine());
                if (LessonNumber > 0 && LessonNumber <= 10) {
                    int summaryminutes = 45 * (LessonNumber) + 5 * ((LessonNumber) / 2) + 15 * ((LessonNumber - 1) / 2);
                    Hours = summaryminutes / 60;
                    LessonMinutes = summaryminutes % 60;
                    break;
                } else System.out.println("Please enter the number from 1 till 10 and try again...");
            } catch (NumberFormatException exception) {
                System.out.println("You entered incorrectly symbol, enter Num pls and try again...");
            }
        }
        return "The ending time of " + LessonNumber + " lesson is " + (9 + Hours + ":" + LessonMinutes);
    }
}
