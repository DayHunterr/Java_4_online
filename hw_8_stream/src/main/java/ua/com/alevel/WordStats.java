package ua.com.alevel;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class WordStats {
    public static void main(String[] args) throws IOException {

        String leftAlignFormat = "| %-15s | %-6d | %-6d | %-11.2f |%n";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter your text: ");

        String text = reader.readLine();

        Map<String, Long> countWords = Arrays.stream(text.split("\\W+")).collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));

        int totalWords = countWords.values().stream().mapToInt(Long::intValue).sum();

        AtomicInteger rating = new AtomicInteger(1);

        System.out.format("+-----------------+--------+--------+-------------+%n");
        System.out.format("|       Words     | Rating |  Count |  Percentage |%n");
        System.out.format("+-----------------+--------+--------+-------------+%n");

        countWords.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(entry -> {
                    String word = entry.getKey();
                    long count = entry.getValue();
                    double percent = count * 100.0 / (double) totalWords;
                    return String.format(leftAlignFormat, word, rating.getAndIncrement(), count, percent);
                })
                .forEach(System.out::println);
        System.out.format("+-----------------+--------+--------+-------------+%n");
    }
}