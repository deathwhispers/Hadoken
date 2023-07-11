package pers.guangjian.hadoken.connector.core.message.firmware;

import pers.guangjian.hadoken.connector.core.message.CommonDeviceMessageResponse;
import pers.guangjian.hadoken.connector.core.message.MessageType;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * // * 读取固件信息响应 设备 -> 平台
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 14:12
 */
@Getter
@Setter
public class ReadFirmwareMessageResponse extends CommonDeviceMessageResponse<ReadFirmwareMessageResponse> {

    //固件版本号
    private String version;

    //其他信息
    private Map<String, Object> attributes;

    @Override
    public MessageType getMessageType() {
        return MessageType.READ_FIRMWARE_RESPONSE;
    }

}
