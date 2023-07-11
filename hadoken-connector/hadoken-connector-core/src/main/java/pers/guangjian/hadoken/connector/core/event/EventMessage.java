package pers.guangjian.hadoken.connector.core.event;

import pers.guangjian.hadoken.connector.core.message.CommonDeviceMessage;
import pers.guangjian.hadoken.connector.core.message.MessageType;
import lombok.Getter;
import lombok.Setter;

/**
 * 事件消息
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 14:02
 */
@Getter
@Setter
public class EventMessage extends CommonDeviceMessage {

    private String event;

    private Object data;

    public MessageType getMessageType() {
        return MessageType.EVENT;
    }

}
