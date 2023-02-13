package ua.com.alevel;

public class CustomMap<K, V> {

    private Bucket[] buckets = new CustomMap.Bucket[16];
    private Bucket[] buckets1 = new Bucket[16];

    private static class Bucket {
        private Node node;
    }

    protected static class Node {
//        private K key;
//        private V value;
//        private int hash;
//        private Node next;
    }
}
