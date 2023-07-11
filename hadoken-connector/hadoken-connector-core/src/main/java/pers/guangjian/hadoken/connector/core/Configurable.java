package pers.guangjian.hadoken.connector.core;

import pers.guangjian.hadoken.connector.core.config.ConfigKey;
import pers.guangjian.hadoken.connector.core.config.ConfigKeyValue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 可配置接口
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/10 16:44
 */
public interface Configurable {

    /**
     * 获取配置,如果值不存在则返回空
     *
     * @param key key
     * @return 结果包装器, 不会为null
     * @see Value#get()
     */
    Value getConfig(String key);

    /**
     * 获取多个配置信息
     *
     * @param keys 配置key集合
     * @return 配置信息
     */
    Values getConfigs(Collection<String> keys);

    /**
     * 设置一个配置,配置最好以基本数据类型或者json为主
     *
     * @param key   配置key
     * @param value 值 不能为null
     */
    Boolean setConfig(String key, Object value);

    default Boolean setConfig(ConfigKeyValue<?> keyValue) {
        return setConfig(keyValue.getKey(), keyValue.getValue());
    }

    default <T> Boolean setConfig(ConfigKey<T> key, T value) {
        return setConfig(key.getKey(), value);
    }

    default Boolean setConfigs(ConfigKeyValue<?>... keyValues) {
        return setConfigs(Arrays.stream(keyValues)
                .filter(ConfigKeyValue::isNotNull)
                .collect(Collectors.toMap(ConfigKeyValue::getKey, ConfigKeyValue::getValue)));
    }

    default <V> V getConfig(ConfigKey<V> key) {
        Value config = getConfig(key.getKey());
        return config.as(key.getType());
    }

    default Values getConfigs(ConfigKey<?>... key) {
        return getConfigs(Arrays.stream(key)
                .map(ConfigKey::getKey)
                .collect(Collectors.toSet()));
    }

    /**
     * 获取多个配置,如果未指定key,则获取全部配置
     *
     * @return 所有配置结果集合
     */
    default Values getConfigs(String... keys) {
        return getConfigs(Arrays.asList(keys));
    }

    /**
     * 批量设置配置
     *
     * @param conf 配置内容
     */
    Boolean setConfigs(Map<String, Object> conf);

    /**
     * 删除配置
     *
     * @param key key
     */
    Boolean removeConfig(String key);

    /**
     * 获取并删除配置
     *
     * @param key key
     * @return 被删除的配置
     * @since 1.1.1
     */
    Value getAndRemoveConfig(String key);

    /**
     * 删除配置
     *
     * @param key key
     * @return 被删除的值，不存在则返回empty
     */
    Boolean removeConfigs(Collection<String> key);

    /**
     * 刷新配置信息
     *
     * @return key
     */
    Void refreshConfig(Collection<String> keys);

    /**
     * 刷新全部配置信息
     *
     * @return key
     */
    Void refreshAllConfig();

    /**
     * 删除多个配置信息
     *
     * @param key key
     * @return 删除结果
     */
    default Boolean removeConfigs(ConfigKey<?>... key) {
        return removeConfigs(Arrays.stream(key).map(ConfigKey::getKey).collect(Collectors.toSet()));
    }


}
