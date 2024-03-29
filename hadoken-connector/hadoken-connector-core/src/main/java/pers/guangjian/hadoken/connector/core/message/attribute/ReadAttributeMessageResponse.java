package pers.guangjian.hadoken.connector.core.message.attribute;

import cn.hutool.json.JSONObject;
import pers.guangjian.hadoken.connector.core.message.CommonDeviceMessageResponse;
import pers.guangjian.hadoken.connector.core.message.MessageType;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 读取设备属性消息回复, 方向: 设备->平台
 * 在设备接收到 {@link ReadAttributeMessage} 消息后,使用此消息进行回复,回复后,指令发起方将收到响应结果.
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 14:08
 */
@Getter
@Setter
public class ReadAttributeMessageResponse extends CommonDeviceMessageResponse<ReadAttributeMessageResponse> {


    /**
     * 回复的属性,key为物模型中的属性ID,value为物模型对应的类型值.
     * <p>
     * 注意: value如果是结构体(对象类型),请勿传入在协议包中自定义的对象,应该转为{@link Map}传入.
     */
    private Map<String, Object> attributes;

    public static ReadAttributeMessageResponse create() {
        ReadAttributeMessageResponse response = new ReadAttributeMessageResponse();

        response.setTimestamp(System.currentTimeMillis());

        return response;
    }

    public ReadAttributeMessageResponse success(Map<String, Object> attributes) {

        this.attributes = attributes;
        super.setSuccess(true);
        return this;

    }

    @Override
    public void fromJson(JSONObject jsonObject) {
        super.fromJson(jsonObject);
        this.attributes = jsonObject.getJSONObject("attributes");
    }

    public MessageType getMessageType() {
        return MessageType.READ_ATTRIBUTE_RESPONSE;
    }
}
