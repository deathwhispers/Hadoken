package pers.guangjian.hadoken.connector.core.device;

import pers.guangjian.hadoken.connector.core.config.ConfigKey;
import pers.guangjian.hadoken.connector.core.config.ConfigKeyValue;
import lombok.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 11:12
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Device implements Serializable {
    private static final long serialVersionUID = -415646165L;

    private String id;

    private String productId;

    private String protocol;

    private String metadata;

    private Map<String, Object> configuration = new HashMap<>();

    // 设备属性
    private Map<String, Object> attributes = new HashMap<>();

    public Device addConfig(String key, Object value) {
        if (configuration == null) {
            configuration = new HashMap<>();
        }
        configuration.put(key, value);
        return this;
    }

    public Device addConfigs(Map<String, ?> configs) {
        if (configs == null) {
            return this;
        }
        configs.forEach(this::addConfig);
        return this;
    }

    public <T> Device addConfig(ConfigKey<T> key, T value) {
        addConfig(key.getKey(), value);
        return this;
    }

    public <T> Device addConfig(ConfigKeyValue<T> keyValue) {
        addConfig(keyValue.getKey(), keyValue.getValue());
        return this;
    }

}
