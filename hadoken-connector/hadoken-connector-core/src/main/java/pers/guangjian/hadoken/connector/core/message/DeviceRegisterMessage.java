package pers.guangjian.hadoken.connector.core.message;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 14:05
 */
@Getter
@Setter
public class DeviceRegisterMessage extends CommonDeviceMessage {
    public MessageType getMessageType() {
        return MessageType.REGISTER;
    }
}
