package pers.guangjian.hadoken.connector.core.things;

import pers.guangjian.hadoken.connector.core.Configurable;
import pers.guangjian.hadoken.connector.core.Value;
import pers.guangjian.hadoken.connector.core.Values;
import pers.guangjian.hadoken.connector.core.Wrapper;
import pers.guangjian.hadoken.connector.core.config.ConfigKey;
import pers.guangjian.hadoken.connector.core.device.DeviceConfigKey;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/10 16:44
 */
public interface Thing extends Configurable, Wrapper {

    /**
     * @return 物ID
     */
    String getId();

    /**
     * @return 物类型
     */
    ThingType getType();

    /**
     * 获取当前物使用的模版
     *
     * @return 当前物使用的模版
     */
    <T extends ThingTemplate> T getTemplate();

    /**
     * 重置物的物模型,重置后物模型将使用模版的物模型
     *
     * @return void
     */
    void resetMetadata();

    /**
     * 获取当前物的物模型,如果当前物没有单独配置物模型,则获取模版里的物模型
     *
     * @return 物模型
     */
    <T extends ThingMetadata> T getMetadata();

    /**
     * 更新物模型
     *
     * @param metadata 物模型json
     * @return true
     */
    Boolean updateMetadata(String metadata);

    /**
     * 更新物模型
     *
     * @param metadata 物模型对象
     * @return true
     */
    Boolean updateMetadata(ThingMetadata metadata);

    /**
     * 获取自身的配置,如果配置不存在则返回
     *
     * @param key 配置Key
     * @return 配置值
     */
    Value getSelfConfig(String key);

    /**
     * 获取自身的多个配置,不会返回,通过从{@link Values}中获取对应的值
     *
     * @param keys 配置key列表
     * @return 配置值
     */
    Values getSelfConfigs(Collection<String> keys);

    /**
     * 获取自身的多个配置
     *
     * @param keys 配置key列表
     * @return 配置值
     */
    default Values getSelfConfigs(String... keys) {
        return getSelfConfigs(Arrays.asList(keys));
    }

    /**
     * 获取自身的配置
     *
     * @param key 配置key
     * @return 配置值
     * @see DeviceConfigKey
     */
    default <V> V getSelfConfig(ConfigKey<V> key) {
        return getSelfConfig(key.getKey()).as(key.getType());
    }

    /**
     * 获取自身的多个配置
     *
     * @param keys 配置key
     * @return 配置值
     * @see DeviceConfigKey
     */
    default Values getSelfConfigs(ConfigKey<?>... keys) {
        return getSelfConfigs(Arrays.stream(keys).map(ConfigKey::getKey).collect(Collectors.toSet()));
    }

    /**
     * 获取RPC操作接口
     *
     * @return ThingRpcSupport
     */
    default ThingRpcSupport rpc() {
        throw new UnsupportedOperationException();
    }
}
