package pers.guangjian.hadoken.connector.core.device;

import pers.guangjian.hadoken.connector.core.ProtocolSupport;
import pers.guangjian.hadoken.connector.core.metadata.DeviceMetadata;
import pers.guangjian.hadoken.connector.core.things.ThingTemplate;

import java.util.List;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 9:16
 */
public interface DeviceProductOperator extends ThingTemplate {

    String getId();

    /**
     * @return 设备产品物模型
     */
    DeviceMetadata getMetadata();

    /**
     * 更新设备型号元数据信息
     *
     * @param metadata 元数据信息
     */
    Boolean updateMetadata(String metadata);

    /**
     * @return 获取协议支持
     */
    ProtocolSupport getProtocol();

    /**
     * @return 获取产品下的所有设备
     */
    List<DeviceOperator> getDevices();
}
