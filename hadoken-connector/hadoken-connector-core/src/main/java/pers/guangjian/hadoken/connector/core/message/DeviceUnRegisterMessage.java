package pers.guangjian.hadoken.connector.core.message;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 14:06
 */
@Getter
@Setter
public class DeviceUnRegisterMessage extends CommonDeviceMessage {
    @Override
    public MessageType getMessageType() {
        return MessageType.UN_REGISTER;
    }
}
