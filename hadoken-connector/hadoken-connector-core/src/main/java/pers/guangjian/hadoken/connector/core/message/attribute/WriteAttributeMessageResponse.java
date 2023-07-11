package pers.guangjian.hadoken.connector.core.message.attribute;

import cn.hutool.json.JSONObject;
import pers.guangjian.hadoken.connector.core.message.CommonDeviceMessageResponse;
import pers.guangjian.hadoken.connector.core.message.MessageType;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 14:08
 */
@Getter
@Setter
public class WriteAttributeMessageResponse extends CommonDeviceMessageResponse<WriteAttributeMessageResponse> {

    private Map<String, Object> attributes;

    public synchronized WriteAttributeMessageResponse addAttribute(String key, Object value) {
        if (attributes == null) {
            attributes = new LinkedHashMap<>();
        }
        attributes.put(key, value);
        return this;
    }


    public static WriteAttributeMessageResponse create() {
        WriteAttributeMessageResponse Response = new WriteAttributeMessageResponse();

        Response.setTimestamp(System.currentTimeMillis());

        return Response;
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
        return MessageType.WRITE_ATTRIBUTE_RESPONSE;
    }

}
