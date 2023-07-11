package pers.guangjian.hadoken.connector.core.message.firmware;

import pers.guangjian.hadoken.connector.core.message.CommonDeviceMessage;
import pers.guangjian.hadoken.connector.core.message.MessageType;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 上报设备固件信息 设备 -> 平台
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 14:12
 */
@Getter
@Setter
public class ReportFirmwareMessage extends CommonDeviceMessage {

    //版本号
    private String version;

    //其他属性
    private Map<String, Object> attributes;

    @Override
    public MessageType getMessageType() {
        return MessageType.REPORT_FIRMWARE;
    }
}
