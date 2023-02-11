package ua.com.alevel.util;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

    public class DictionaryTest {
        @Test
        @Order(1)
        public void testSize() {
            Dictionary<String, Integer> dictionary = new Dictionary<>();
            dictionary.put("Key1", 1);
            dictionary.put("Key2", 2);
            dictionary.put("Key3", 3);
            assertEquals(3, dictionary.size());
        }

        @Test
        @Order(2)
        public void testIsEmpty() {
            Dictionary<String, Integer> dictionary = new Dictionary<>();
            assertTrue(dictionary.isEmpty());
            dictionary.put("Key1", 1);
            assertFalse(dictionary.isEmpty());
        }

        @Test
        @Order(3)
        public void testContainsKey() {
            Dictionary<String, Integer> dictionary = new Dictionary<>();
            dictionary.put("Key1", 1);
            dictionary.put("Key2", 2);
            assertTrue(dictionary.containsKey("Key1"));
            assertFalse(dictionary.containsKey("Key3"));
        }

        @Test
        @Order(4)
        public void testContainsValue() {
            Dictionary<String, Integer> dictionary = new Dictionary<>();
            dictionary.put("Key1", 1);
            dictionary.put("Key2", 2);
            assertTrue(dictionary.containsValue(1));
            assertFalse(dictionary.containsValue(3));
        }

        @Test
        @Order(5)
        public void testGet() {
            Dictionary<String, Integer> dictionary = new Dictionary<>();
            dictionary.put("Key1", 1);
            dictionary.put("Key2", 2);
            assertEquals(1, (int) dictionary.get("Key1").get());
        }

        @Test
        @Order(6)
        public void testPut() {
            Dictionary<String, Integer> dictionary = new Dictionary<>();
            assertTrue(dictionary.put("Key1", 1));
            assertEquals(1, (int) dictionary.get("Key1").get());
            assertTrue(dictionary.put("Key1", 2));
            assertEquals(2, (int) dictionary.get("Key1").get());
        }

        @Test
        @Order(7)
        public void testRemove() {
            Dictionary<String, Integer> dictionary = new Dictionary<>();
            dictionary.put("Key1", 1);
            dictionary.put("Key2", 2);
            assertTrue(dictionary.remove("Key1"));
            assertFalse(dictionary.containsKey("Key1"));
            assertFalse(dictionary.remove("Key3"));
        }

        @Test
        @Order(8)
        public void testPutAll() {
            Dictionary<String, Integer> dictionary1 = new Dictionary<>();
            dictionary1.put("Key1", 1);
            dictionary1.put("Key2", 2);
            Dictionary<String, Integer> dictionary2 = new Dictionary<>();
            dictionary2.put("Key3", 3);
            dictionary2.put("Key4", 4);
            assertTrue(dictionary1.putAll(dictionary2));
            assertEquals(4, dictionary1.size());
            assertTrue(dictionary1.containsKey("Key3"));
            assertTrue(dictionary1.containsKey("Key4"));
        }

        @Test
        @Order(9)
        public void testClear() {
            Dictionary<String, Integer> dictionary = new Dictionary<>();
            dictionary.put("Key1", 1);
            dictionary.put("Key2", 2);
            dictionary.clear();
            assertTrue(dictionary.isEmpty());
            assertFalse(dictionary.containsKey("Key1"));
            assertFalse(dictionary.containsKey("Key2"));
        }

        @Test
        @Order(10)
        public void testKeySet() {
            Dictionary<String, Integer> dictionary = new Dictionary<>();
            dictionary.put("Key1", 1);
            dictionary.put("Key2", 2);
            Set<String> keys = dictionary.keySet();
            assertEquals(2, keys.size());
            assertTrue(keys.contains("Key1"));
            assertTrue(keys.contains("Key2"));
        }

        @Test
        @Order(11)
        public void testValues() {
            Dictionary<String, Integer> dictionary = new Dictionary<>();
            dictionary.put("Key1", 1);
            dictionary.put("Key2", 2);
            Collection<Integer> values = dictionary.values();
            assertEquals(2, values.size());
            assertTrue(values.contains(1));
            assertTrue(values.contains(2));
        }
    }