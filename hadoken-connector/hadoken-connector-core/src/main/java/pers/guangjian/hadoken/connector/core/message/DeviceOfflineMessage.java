package pers.guangjian.hadoken.connector.core.message;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 14:05
 */
public class DeviceOfflineMessage extends CommonDeviceMessage {
    public MessageType getMessageType() {
        return MessageType.OFFLINE;
    }
}
