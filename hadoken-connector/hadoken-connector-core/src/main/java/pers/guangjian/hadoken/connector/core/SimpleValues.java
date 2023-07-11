package pers.guangjian.hadoken.connector.core;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.apache.commons.collections4.MapUtils;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/10 16:47
 */
@AllArgsConstructor(staticName = "of")
public class SimpleValues implements Values {

    @NonNull
    private final Map<String, Object> values;

    @Override
    public Map<String, Object> getAllValues() {
        return new HashMap<>(values);
    }

    @Override
    public Optional<Value> getValue(String key) {
        return Optional
                .ofNullable(key)
                .map(values::get)
                .map(Value::simple);
    }

    @Override
    public Values merge(Values source) {
        Map<String, Object> merged = new HashMap<>();
        merged.putAll(this.values);
        merged.putAll(source.getAllValues());
        return of(merged);
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public Set<String> getNonExistentKeys(Collection<String> keys) {
        return keys
                .stream()
                .filter(has -> !values.containsKey(has))
                .collect(Collectors.toSet());
    }

    @Override
    public String getString(String key, Supplier<String> defaultValue) {
        if (MapUtils.isEmpty(values)) {
            return defaultValue.get();
        }
        Object val = values.get(key);
        if (val == null) {
            return defaultValue.get();
        }
        return String.valueOf(val);
    }

    @Override
    public Number getNumber(String key, Supplier<Number> defaultValue) {
        if (MapUtils.isEmpty(values)) {
            return defaultValue.get();
        }
        Object val = values.get(key);
        if (val == null) {
            return defaultValue.get();
        }
        if (val instanceof Number) {
            return ((Number) val);
        }
        return null;
    }
}
