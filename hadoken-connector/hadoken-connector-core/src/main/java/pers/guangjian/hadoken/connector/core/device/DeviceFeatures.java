package pers.guangjian.hadoken.connector.core.device;

import pers.guangjian.hadoken.connector.core.metadata.Feature;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/12 9:59
 */
@Getter
@AllArgsConstructor
public enum DeviceFeatures implements Feature {

    //标识使用此协议的设备支持固件升级
    supportFirmware("支持固件升级");

    private final String name;

    @Override
    public String getId() {
        return name();
    }

    @Override
    public String getType() {
        return "device-manage";
    }
}
