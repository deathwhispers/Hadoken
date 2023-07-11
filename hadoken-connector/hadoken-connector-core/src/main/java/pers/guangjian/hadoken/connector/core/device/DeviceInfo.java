package pers.guangjian.hadoken.connector.core.device;

import pers.guangjian.hadoken.connector.core.ProtocolSupport;
import pers.guangjian.hadoken.connector.core.config.ConfigKey;
import pers.guangjian.hadoken.connector.core.config.ConfigKeyValue;
import lombok.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/12 10:02
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceInfo implements Serializable {
    private static final long serialVersionUID = -6849794470754667710L;

    /**
     * 设备ID
     */
    private String id;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 产品版本
     */
    private String productVersion;

    /**
     * 消息协议
     *
     * @see ProtocolSupport#getId()
     */
    private String protocol;

    /**
     * 物模型
     */
    private String metadata;

    /**
     * 其他配置
     */
    private Map<String, Object> configuration = new HashMap<>();

    public DeviceInfo addConfig(String key, Object value) {
//        if (value == null) {
//            return this;
//        }
        if (configuration == null) {
            configuration = new HashMap<>();
        }
        configuration.put(key, value);
        return this;
    }

    public DeviceInfo addConfigIfAbsent(String key, Object value) {
        if (configuration == null) {
            configuration = new HashMap<>();
        }
        configuration.putIfAbsent(key, value);
        return this;
    }

    public DeviceInfo addConfigs(Map<String, ?> configs) {
        if (configs == null) {
            return this;
        }
        configs.forEach(this::addConfig);
        return this;
    }

    public <T> DeviceInfo addConfig(ConfigKey<T> key, T value) {
        addConfig(key.getKey(), value);
        return this;
    }

    public <T> DeviceInfo addConfig(ConfigKeyValue<T> keyValue) {
        addConfig(keyValue.getKey(), keyValue.getValue());
        return this;
    }

}
