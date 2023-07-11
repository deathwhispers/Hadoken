package pers.guangjian.hadoken.connector.core.device;

import pers.guangjian.hadoken.connector.core.ProtocolSupport;
import pers.guangjian.hadoken.connector.core.Value;
import pers.guangjian.hadoken.connector.core.Values;
import pers.guangjian.hadoken.connector.core.config.ConfigKey;
import pers.guangjian.hadoken.connector.core.metadata.DeviceMetadata;
import pers.guangjian.hadoken.connector.core.things.Thing;
import pers.guangjian.hadoken.connector.core.things.ThingType;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 设备操作接口,用于发送指令到设备messageSender()以及获取配置等相关信息
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/10 16:42
 */
public interface DeviceOperator extends Thing {

    @Override
    default ThingType getType() {
        return DeviceThingType.device;
    }

    /**
     * @return 设备ID
     */
    String getDeviceId();

    /**
     * @return 当前设备连接所在服务器ID，如果设备未上线 {@link DeviceState#online}，则返回<code>null</code>
     */
    String getConnectionServerId();

    /**
     * @return 当前设备连接会话ID
     */
    String getSessionId();

    /**
     * 获取设备地址,通常是ip地址.
     *
     * @return 地址
     */
    String getAddress();

    /**
     * 设置设备地址
     *
     * @param address 地址
     */
    void setAddress(String address);

    /**
     * @param state 状态
     * @see DeviceState#online
     */
    Boolean putState(byte state);

    /**
     * 获取设备当前缓存的状态,此状态可能与实际的状态不一致.
     *
     * @return 获取当前状态
     * @see DeviceState
     */
    Byte getState();

    /**
     * 检查设备的真实状态,此操作将检查设备真实的状态.
     * 如果设备协议中指定了{@link ProtocolSupport#getStateChecker()},则将调用指定的状态检查器进行检查.
     * <br>
     * 默认的状态检查逻辑:
     * <br>
     * <img src="doc-files/device-state-check.svg">
     *
     * @see DeviceStateChecker
     */
    Byte checkState();

    /**
     * @return 上线时间
     */
    Long getOnlineTime();

    /**
     * @return 离线时间
     */
    Long getOfflineTime();

    /**
     * 设备上线,通常不需要手动调用
     *
     * @param serverId  设备所在服务ID
     * @param sessionId 会话ID
     */
    default Boolean online(String serverId, String sessionId) {
        return online(serverId, sessionId, null);
    }

    Boolean online(String serverId, String sessionId, String address);

    /**
     * 获取设备自身的配置,如果配置不存在则返回空
     *
     * @param key 配置Key
     * @return 配置值
     */
    Value getSelfConfig(String key);

    /**
     * 获取设备自身的多个配置,通过从{@link Values}中获取对应的值
     *
     * @param keys 配置key列表
     * @return 配置值
     */
    Values getSelfConfigs(Collection<String> keys);

    /**
     * 获取设备自身的多个配置
     *
     * @param keys 配置key列表
     * @return 配置值
     */
    default Values getSelfConfigs(String... keys) {
        return getSelfConfigs(Arrays.asList(keys));
    }

    /**
     * 获取设备自身的配置
     *
     * @param key 配置key
     * @return 配置值
     * @see DeviceConfigKey
     */
    default <V> V getSelfConfig(ConfigKey<V> key) {
        return getSelfConfig(key.getKey()).as(key.getType());
    }

    /**
     * 获取设备自身的多个配置
     *
     * @param keys 配置key
     * @return 配置值
     * @see DeviceConfigKey
     */
    default Values getSelfConfigs(ConfigKey<?>... keys) {
        return getSelfConfigs(Arrays.stream(keys).map(ConfigKey::getKey).collect(Collectors.toSet()));
    }

    /**
     * @return 是否在线
     */
    default Boolean isOnline() {
        if (checkState()==null) {
            return false;
        }
        return checkState().equals(DeviceState.online);
    }

    /**
     * 设置设备离线
     *
     * @see DeviceState#offline
     */
    Boolean offline();

    /**
     * 断开设备连接
     *
     * @return 断开结果
     */
    Boolean disconnect();

    /**
     * 进行授权
     *
     * @param request 授权请求
     * @return 授权结果
     * @see MqttAuthenticationRequest
     */
    AuthenticationResponse authenticate(AuthenticationRequest request);

    /**
     * @return 获取设备的元数据
     */
    DeviceMetadata getMetadata();

    /**
     * @return 获取此设备使用的协议支持
     */
    ProtocolSupport getProtocol();

    /**
     * @return 消息发送器, 用于发送消息给设备
     */
    DeviceMessageSender messageSender();

    /**
     * 设置当前设备的独立物模型,如果没有设置,这使用产品的物模型配置
     *
     * @param metadata 物模型
     */
    Boolean updateMetadata(String metadata);

    /**
     * 重置当前设备的独立物模型
     *
     * @return void
     * @since 1.1.6
     */
    void resetMetadata();

    /**
     * @return 获取设备对应的产品操作接口
     */
    DeviceProductOperator getProduct();

    @Override
    default DeviceProductOperator getTemplate() {
        return getProduct();
    }
}
