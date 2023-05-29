package ua.com.alevel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeyValueDTO<K, V> {

    K key;
    V value;

    public KeyValueDTO(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
