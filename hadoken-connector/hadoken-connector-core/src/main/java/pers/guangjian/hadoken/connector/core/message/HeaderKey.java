package pers.guangjian.hadoken.connector.core.message;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 14:44
 */
public interface HeaderKey<T> {

    String getKey();

    T getDefaultValue();

    default Class<T> getType(){
        return getDefaultValue() == null ? (Class<T>) Object.class : (Class<T>) getDefaultValue().getClass();
    }

    static <T> HeaderKey<T> of(String key, T defaultValue, Class<T> type) {
        return new HeaderKey<T>() {
            @Override
            public String getKey() {
                return key;
            }

            @Override
            public T getDefaultValue() {
                return defaultValue;
            }

            @Override
            public Class<T> getType() {
                return type;
            }
        };
    }

    @SuppressWarnings("all")
    static <T> HeaderKey<T> of(String key, T defaultValue) {
        return of(key, defaultValue, defaultValue == null ? (Class<T>) Object.class : (Class<T>) defaultValue.getClass());
    }
}
