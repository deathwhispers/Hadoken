package pers.guangjian.hadoken.connector.core;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 9:17
 */
public interface Wrapper {
    default boolean isWrapperFor(Class<?> type) {
        return type.isInstance(this);
    }

    default <T> T unwrap(Class<T> type) {
        return type.cast(this);
    }

}
