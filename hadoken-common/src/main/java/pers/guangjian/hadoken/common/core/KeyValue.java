package pers.guangjian.hadoken.common.core;

import lombok.Data;

/**
 * @author yanggj
 *  Key Value 的键值对
 * @date 2022/02/28 15:53
 * @version 1.0.0
 */
@Data
public class KeyValue<K, V> {

    private K key;
    private V value;

    public KeyValue() {
    }

    public KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
