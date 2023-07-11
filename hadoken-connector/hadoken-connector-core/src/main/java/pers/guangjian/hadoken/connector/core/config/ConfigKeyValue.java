package pers.guangjian.hadoken.connector.core.config;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 11:17
 */
public interface ConfigKeyValue<V> extends ConfigKey<V> {

    V getValue();

    default boolean isNull() {
        return null == getValue();
    }

    default boolean isNotNull() {
        return null != getValue();
    }

}
