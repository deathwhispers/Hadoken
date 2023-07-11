package pers.guangjian.hadoken.connector.core.codec;

import pers.guangjian.hadoken.connector.core.Payload;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 10:28
 */
public interface Encoder<T> {

    Payload encode(T body);
}
