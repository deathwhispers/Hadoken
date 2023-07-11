package pers.guangjian.hadoken.connector.core;

import java.util.Date;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/10 16:46
 */
public interface Value {
    /**
     * 转为String
     *
     * @return String的值
     * @see String#valueOf(int)
     */
    default String asString() {
        return String.valueOf(get());
    }

    /**
     * 转为Int
     *
     * @return Int值
     */
    default int asInt() {
        return as(Integer.class);
    }

    /**
     * 转为Long
     *
     * @return Long值
     */
    default long asLong() {
        return as(Long.class);
    }

    /**
     * 转为Boolean
     *
     * @return Boolean值
     */
    default boolean asBoolean() {
        return Boolean.TRUE.equals(get())
                || "true".equals(get());
    }

    /**
     * 转为Number,由具体的值决定实际的Number类型
     *
     * @return Number
     */
    default Number asNumber() {
        return as(Number.class);
    }

    /**
     * 转为Date类型
     *
     * @return Date
     */
    default Date asDate() {
        return as(Date.class);
    }

    /**
     * 获取原始值
     *
     * @return 原始值
     */
    Object get();

    /**
     * 尝试转为指定的类型.可能会抛出{@link  ClassCastException}
     *
     * @param type 类型
     * @param <T>  类型
     * @return 指定类型的值
     */
    <T> T as(Class<T> type);

    /**
     * 包装一个简单的值
     *
     * @param value 原始值
     * @return 值
     */
    static Value simple(Object value) {
        return SimpleValue.of(value);
    }
}
