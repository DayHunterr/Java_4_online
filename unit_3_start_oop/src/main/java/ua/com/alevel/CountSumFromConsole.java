package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;

//1. реализуйте задачу, которая принимает строку с консоли и вычленяет все числа и находит их сумму
//        Пример:
//        Входные данные: 1w4tt!7 Выходные данные: 12

public class CountSumFromConsole {

    public static int CountSum(BufferedReader reader) throws IOException {
       String input = reader.readLine();
       int sum = 0;
        for (char c: input.toCharArray()) {
            if(Character.isDigit(c)) {
                sum += Character.getNumericValue(c);
            }
        }
        return sum;
    }
}
