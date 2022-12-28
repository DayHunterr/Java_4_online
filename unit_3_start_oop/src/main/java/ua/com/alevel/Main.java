package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        System.out.println("First task....");
        System.out.print("Enter a few digits and characters or specific symbols: ");
        int sum = CountSumFromConsole.CountSum(br);
        System.out.println("Sum of your entered numbers " + sum);
        System.out.println("------------------------------------");
        System.out.println("Second task....");
        System.out.print("Enter a few digits and characters or specific symbols again: ");
        String[] count = CountSymbFromConsole.CountSumSymb(br);
        System.out.print("Result: ");
        for (String b : count) {
            System.out.print(b + " ");
        }
        System.out.println("\n------------------------------------");
        System.out.println("Third task....");
        String schedule = SchoolSchedule.schedule(br);
        System.out.println(schedule);
    }
}
