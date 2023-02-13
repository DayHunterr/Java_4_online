package ua.com.alevel.util;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Dictionary<String,Integer> dictionary = new Dictionary<>();
        for (int i = 0; i < 50; i++) {
            dictionary.put("Key" + i,i);
        }
        Collection <?> collection = dictionary.values();
        System.out.println(collection);
    }
}
