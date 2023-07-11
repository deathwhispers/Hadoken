package pers.guangjian.hadoken.connector.core.device;

import pers.guangjian.hadoken.connector.core.config.ConfigKey;
import pers.guangjian.hadoken.connector.core.config.ConfigKeyValue;
import lombok.*;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/12 10:28
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo implements Serializable {
    private static final long serialVersionUID = -6849794470754667710L;

    /**
     * 设备ID
     */
    private String id;

    /**
     * 消息协议
     */
    private String protocol;

    /**
     * 元数据
     */
    private String metadata;

    /**
     * 其他配置
     */
    private Map<String, Object> configuration = new HashMap<>();

    /**
     * 版本号
     */
    private String version;

    public ProductInfo(String id, String protocol, String metadata) {
        this.id = id;
        this.protocol = protocol;
        this.metadata = metadata;
    }

    public ProductInfo addConfig(String key, Object value) {
        if (StringUtils.isEmpty(value)) {
            return this;
        }
        if (configuration == null) {
            configuration = new HashMap<>();
        }
        configuration.put(key, value);
        return this;
    }

    public ProductInfo addConfigs(Map<String, ?> configs) {
        if (configs == null) {
            return this;
        }
        configs.forEach(this::addConfig);
        return this;
    }

    public <T> ProductInfo addConfig(ConfigKey<T> key, T value) {
        addConfig(key.getKey(), value);
        return this;
    }

    public <T> ProductInfo addConfig(ConfigKeyValue<T> keyValue) {
        addConfig(keyValue.getKey(), keyValue.getValue());
        return this;
    }
}
