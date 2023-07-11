package pers.guangjian.hadoken.connector.core.message.attribute;

import pers.guangjian.hadoken.connector.core.message.CommonDeviceMessage;
import pers.guangjian.hadoken.connector.core.message.MessageType;
import pers.guangjian.hadoken.connector.core.message.RepayableDeviceMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 14:08
 */
@Getter
@Setter
public class WriteAttributeMessage extends CommonDeviceMessage implements RepayableDeviceMessage<WriteAttributeMessageResponse> {

    /**
     * 要修改的属性，key 为物模型中对应的属性ID,value为属性值
     */
    private Map<String, Object> attributes = new LinkedHashMap<>();

    public void addAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    @Override
    public WriteAttributeMessageResponse newResponse() {
        return new WriteAttributeMessageResponse().from(this);
    }

    public MessageType getMessageType() {
        return MessageType.WRITE_ATTRIBUTE;
    }

}
