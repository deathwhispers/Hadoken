package pers.guangjian.hadoken.connector.core;

import lombok.AllArgsConstructor;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/10 16:47
 */
@AllArgsConstructor(staticName = "of")
public class SimpleValue implements Value {

    private final Object nativeValue;

    @Override
    public Object get() {
        return nativeValue;
    }

    @Override
    public <T> T as(Class<T> type) {
        if (nativeValue == null) {
            return null;
        }
        if (type.isInstance(nativeValue)) {
            return (T) nativeValue;
        }
        return null;
    }
}
