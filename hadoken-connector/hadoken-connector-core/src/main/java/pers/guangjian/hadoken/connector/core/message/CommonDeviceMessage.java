package pers.guangjian.hadoken.connector.core.message;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 16:04
 */
@Getter
@Setter
public class CommonDeviceMessage implements DeviceMessage {

    private String code;

    private String messageId;

    private String deviceId;

    private Map<String, Object> headers;

    private long timestamp = System.currentTimeMillis();

    public void setHeaders(Map<String, Object> headers) {
        if (headers != null && !(headers instanceof ConcurrentHashMap)) {
            headers = new ConcurrentHashMap<>(headers);
        }
        this.headers = headers;
    }

    @Override
    @JsonIgnore
    //@JSONField(serialize = false)
    public final String getThingId() {
        return getDeviceId();
    }

    @Override
    @JsonIgnore
    //@JSONField(serialize = false)
    public final String getThingType() {
        return DeviceMessage.super.getThingType();
    }

    @Override
    public CommonDeviceMessage messageId(String messageId) {
        setMessageId(messageId);
        return this;
    }

    @Override
    public CommonDeviceMessage thingId(String thingType, String thingId) {
        this.setDeviceId(thingId);
        return this;
    }

    private Map<String, Object> safeGetHeader() {
        return headers == null ? headers = new ConcurrentHashMap<>(64) : headers;
    }

    @Override
    public CommonDeviceMessage timestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public synchronized CommonDeviceMessage addHeader(String header, Object value) {

        if (header != null && value != null) {
            safeGetHeader().put(header, value);
        }
        return this;
    }

    @Override
    public synchronized CommonDeviceMessage addHeaderIfAbsent(String header, Object value) {

        if (header != null && value != null) {
            safeGetHeader().putIfAbsent(header, value);
        }
        return this;
    }

    @Override
    public CommonDeviceMessage removeHeader(String header) {
        if (this.headers != null) {
            this.headers.remove(header);
        }
        return this;
    }

    @Override
    public Object computeHeader(String key, BiFunction<String, Object, Object> computer) {
        return safeGetHeader().compute(key, computer);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = JSONUtil.parseObj(this);
        json.set("messageType", getMessageType().name());
        return json;
    }

    @Override
    public void fromJson(JSONObject jsonObject) {
        DeviceMessage.super.fromJson(jsonObject);
    }

    @Override
    public String toString() {
        return toJson().toString();
    }

}