package pers.guangjian.hadoken.connector.core.codec;

import pers.guangjian.hadoken.connector.core.Payload;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 10:28
 */
public interface Decoder<T> {

    Class<T> forType();

    T decode(Payload payload);

    default boolean isDecodeFrom(Object nativeObject){
        if(nativeObject==null){
            return false;
        }
        return forType().isInstance(nativeObject);
    }
}
