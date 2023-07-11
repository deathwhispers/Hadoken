package pers.guangjian.hadoken.connector.core.message;

import cn.hutool.json.JSONObject;
import lombok.Setter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/12 9:27
 */
public class UpdateTagMessage extends CommonDeviceMessage implements UpdateTingTagsMessage{

    @Setter
    private Map<String, Object> tags;

    public Map<String, Object> getTags() {
        return tags == null ? Collections.emptyMap() : tags;
    }

    public UpdateTagMessage tag(String tag, Object value) {
        if (tags == null) {
            tags = new HashMap<>();
        }
        tags.put(tag, value);
        return this;
    }

    public UpdateTagMessage tags(Map<String, Object> tags) {
        if (this.tags == null) {
            this.tags = tags;
            return this;
        }
        this.tags.putAll(tags);
        return this;
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.UPDATE_TAG;
    }

    @Override
    public void fromJson(JSONObject jsonObject) {
        super.fromJson(jsonObject);
        this.tags = jsonObject.getJSONObject("tags");
    }
}
