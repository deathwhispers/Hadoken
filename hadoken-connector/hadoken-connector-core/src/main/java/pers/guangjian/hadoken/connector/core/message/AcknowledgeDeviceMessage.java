package pers.guangjian.hadoken.connector.core.message;

/**
 * 应答消息,通常用于发送非可回复消息后的应答
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/18 10:14
 */
public class AcknowledgeDeviceMessage extends CommonDeviceMessageResponse<AcknowledgeDeviceMessage> {

    @Override
    public MessageType getMessageType() {
        return MessageType.ACKNOWLEDGE;
    }
}