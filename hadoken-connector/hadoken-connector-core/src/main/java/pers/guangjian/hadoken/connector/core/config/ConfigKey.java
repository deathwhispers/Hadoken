package pers.guangjian.hadoken.connector.core.config;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 11:17
 */
public interface ConfigKey<V> {
    String getKey();

    default String getName() {
        return getKey();
    }

    default Class<V> getType() {
        return (Class<V>) Object.class;
    }

    static <T> ConfigKey<T> of(String key) {
        return of(key, key);
    }

    static <T> ConfigKey<T> of(String key, String name) {
        return SimpleConfigKey.of(key, name, (Class<T>) Object.class);
    }

    static <T> ConfigKey<T> of(String key, String name, Class<T> type) {
        return SimpleConfigKey.of(key, name, type);
    }

    default ConfigKeyValue<V> value(V value) {
        return new ConfigKeyValue<V>() {
            @Override
            public V getValue() {
                return value;
            }

            @Override
            public String getKey() {
                return ConfigKey.this.getKey();
            }

            @Override
            public String getName() {
                return ConfigKey.this.getName();
            }

            @Override
            public Class<V> getType() {
                return ConfigKey.this.getType();
            }
        };
    }

}
