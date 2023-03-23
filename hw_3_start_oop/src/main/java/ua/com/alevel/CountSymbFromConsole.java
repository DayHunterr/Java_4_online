package ua.com.alevel;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

// 2. реализуйте задачу, которая принимает строку с консоли и вычленяет все символы латиницы/кириллицы и сортирует их, указывая количество вхождений каждого символа


public class CountSymbFromConsole {


    public static String[] CountSumSymb(BufferedReader reader) throws IOException {

        String input = reader.readLine(); // читаем строку с консоли

        // фильтруем строку, оставляем только буквы
        StringBuilder filteredInput = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                filteredInput.append(c);
            }
        }

        // создаем массив символов строки
        char[] chars = filteredInput.toString().toCharArray();

        // сортируем массив символов
        Arrays.sort(chars);

         // итерируем по символам, выводим каждый символ и количество его вхождений
        String[] array = new String[chars.length];
        int count = 1;
        int index = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                count++;
            } else {
                array[index++] = chars[i - 1] + " = " + count;
                count = 1;
            }

        }
        array[index] = (chars[chars.length - 1] + " = " + count);

        return Arrays.copyOfRange(array,0,index+1);


    }
}

