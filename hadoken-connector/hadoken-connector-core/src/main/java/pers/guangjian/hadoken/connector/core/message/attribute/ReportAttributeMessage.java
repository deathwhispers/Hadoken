package pers.guangjian.hadoken.connector.core.message.attribute;

import cn.hutool.json.JSONObject;
import pers.guangjian.hadoken.connector.core.message.CommonDeviceMessage;
import pers.guangjian.hadoken.connector.core.message.MessageType;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Optional;

/**
 * 上报设备属性,通常由设备定时上报,方向: 设备->平台
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 14:09
 */
@Getter
@Setter
public class ReportAttributeMessage extends CommonDeviceMessage {


    /**
     * 属性,key为物模型中的属性ID,value为物模型对应的类型值.
     * <p>
     * 注意: value如果是结构体(对象类型),请勿传入在协议包中自定义的对象,应该转为{@link Map}传入.
     */
    private Map<String, Object> attributes;

    public static ReportAttributeMessage create() {
        return new ReportAttributeMessage();
    }

    public ReportAttributeMessage success(Map<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    public Optional<Object> getAttribute(String attribute) {
        return Optional
                .ofNullable(attributes)
                .map(map -> map.get(attribute));
    }

    @Override
    public void fromJson(JSONObject jsonObject) {
        super.fromJson(jsonObject);
        this.attributes = jsonObject.getJSONObject("attributes");
    }

    public MessageType getMessageType() {
        return MessageType.REPORT_ATTRIBUTE;
    }

}
