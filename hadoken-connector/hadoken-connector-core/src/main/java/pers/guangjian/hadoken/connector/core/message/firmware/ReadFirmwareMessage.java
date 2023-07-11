package pers.guangjian.hadoken.connector.core.message.firmware;

import pers.guangjian.hadoken.connector.core.message.CommonDeviceMessage;
import pers.guangjian.hadoken.connector.core.message.MessageType;
import pers.guangjian.hadoken.connector.core.message.RepayableDeviceMessage;

/**
 * 读取固件信息 平台 -> 设备
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 14:11
 */
public class ReadFirmwareMessage extends CommonDeviceMessage implements RepayableDeviceMessage<ReadFirmwareMessageResponse> {

    @Override
    public ReadFirmwareMessageResponse newResponse() {
        return new ReadFirmwareMessageResponse().from(this);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.READ_FIRMWARE;
    }
}
