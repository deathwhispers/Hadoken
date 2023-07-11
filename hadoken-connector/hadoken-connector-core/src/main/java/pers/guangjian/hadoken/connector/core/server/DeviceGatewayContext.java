package pers.guangjian.hadoken.connector.core.server;

import pers.guangjian.hadoken.connector.core.device.DeviceOperator;
import pers.guangjian.hadoken.connector.core.device.DeviceProductOperator;
import pers.guangjian.hadoken.connector.core.message.DeviceMessage;

/**
 * 设备网关上下文,通过上下文可进行设备相关操作
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/12 9:54
 */
public interface DeviceGatewayContext {
    /**
     * 根据ID获取设备操作接口
     *
     * @param deviceId 设备ID
     * @return 设备操作接口
     */
    DeviceOperator getDevice(String deviceId);

    /**
     * 根据产品ID获取产品操作接口
     *
     * @param productId 产品ID
     * @return 产品操作接口
     */
    DeviceProductOperator getProduct(String productId);

    /**
     * 发送设备消息到设备网关,由平台统一处理这个消息.
     *
     * @param message 设备消息
     */
    void onMessage(DeviceMessage message);
}
