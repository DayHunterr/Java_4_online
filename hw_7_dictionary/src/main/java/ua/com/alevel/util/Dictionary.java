package ua.com.alevel.util;

import java.util.*;

public class Dictionary<K,V> {

    private K[] keys;

    private V[] values;

    private int size;

    private static final int DEFAULT_SIZE = 16;

    public Dictionary(){
        keys = (K[]) new Object[DEFAULT_SIZE];
        values = (V[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    int size(){
        return size;
    }

    boolean isEmpty(){
        return size == 0;
    }

    boolean containsKey(K key){
        for (int i = 0; i < size; i++) {
            if(keys[i].equals(key)){
                return true;
            }
        }
        return false;
    }

    boolean containsValue(V value){
        for (int i = 0; i < size; i++) {
            if(values[i].equals(value)){
                return true;
            }
        }
        return false;
    }

    Optional<V> get(K key){
        for (int i = 0; i < size; i++) {
            if(keys[i].equals(key)){
                return Optional.ofNullable(values[i]);
            }
        }
        return Optional.empty();
    }

    private void resize() {
        K[] newKeys = (K[]) new Object[keys.length * 2];
        V[] newValues = (V[]) new Object[values.length * 2];
        for (int i = 0; i < size; i++) {
            newKeys[i] = keys[i];
            newValues[i] = values[i];
        }
        keys = newKeys;
        values = newValues;
    }

    boolean put(K key, V value){
        if(size==keys.length){
            resize();
        }
        for (int i = 0; i < size; i++) {
            if(keys[i].equals(key)){
                values[i] = value;
                return true;
            }
        }
        keys[size] = key;
        values[size] = value;
        size++;
        return true;
    }

    boolean remove(K key){
        for (int i = 0; i < size; i++) {
            if(keys[i].equals(key)){
                keys[i] = keys[size - 1];
                values[i] = values[size - 1];
                values[size - 1] = null;
                keys[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    boolean putAll(Dictionary<K, V> dictionary){
        for (int i = 0; i < dictionary.size(); i++) {
            put(dictionary.keys[i],dictionary.values[i]);
        }
        return true;
    }

    boolean clear(){
        for (int i = 0; i < size; i++) {
            keys[i] = null;
            values[i] = null;
        }
        size = 0;
        return true;
    }

    Set<K> keySet(){
        Set<K> kSet = new HashSet<>();
        for (int i = 0; i < size; i++) {
            kSet.add(keys[i]);
        }
        return kSet;
    }

    Collection<V> values(){
        Collection<V> vCollection = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            vCollection.add(values[i]);
        }
        return vCollection;
    }
}