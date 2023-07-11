package pers.guangjian.hadoken.connector.core.message.attribute;

import pers.guangjian.hadoken.connector.core.message.CommonDeviceMessage;
import pers.guangjian.hadoken.connector.core.message.MessageType;
import pers.guangjian.hadoken.connector.core.message.RepayableDeviceMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 读取设备属性消息, 方向: 平台->设备
 * 下发指令后,设备需要回复指令 {@link ReadAttributeMessageResponse}
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 14:07
 */
@Getter
@Setter
public class ReadAttributeMessage extends CommonDeviceMessage implements RepayableDeviceMessage<ReadAttributeMessageResponse> {

    /**
     * 要读取的属性列表,协议包可根据实际情况处理此参数,
     * 有的设备可能不支持读取指定的属性,则直接读取全部属性返回即可
     */
    private List<String> attributes = new ArrayList<>();

    public void addAttributes(List<String> attributes) {
        this.attributes.addAll(attributes);
    }

    @Override
    public ReadAttributeMessageResponse newResponse() {
        return new ReadAttributeMessageResponse().from(this);
    }

    public MessageType getMessageType() {
        return MessageType.READ_ATTRIBUTE;
    }

}
