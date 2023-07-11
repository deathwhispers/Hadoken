package pers.guangjian.hadoken.connector.core.message;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/12 9:29
 */
@Getter
@Setter
public abstract class CommonThingMessage<SELF extends CommonThingMessage<SELF>> implements ThingMessage {
    private static final long serialVersionUID = -1L;

    private String code;

    private String messageId;

    @Nonnull
    private String thingType;

    @Nonnull
    private String thingId;

    private Map<String, Object> headers;

    public void setHeaders(Map<String, Object> headers) {
        if (headers != null && !(headers instanceof ConcurrentHashMap)) {
            headers = new ConcurrentHashMap<>(headers);
        }
        this.headers = headers;
    }

    private long timestamp = System.currentTimeMillis();

    public abstract MessageType getMessageType();

    @Override
    public SELF thingId(String thingType, String thingId) {
        this.setThingType(thingType);
        this.setThingId(thingId);
        return castSelf();
    }

    @Override
    public SELF timestamp(long timestamp) {
        this.timestamp = timestamp;
        return castSelf();
    }

    @Override
    public synchronized SELF addHeader(String header, Object value) {
        if (header != null && value != null) {
            safeGetHeader().put(header, value);
        }
        return castSelf();
    }

    @Override
    public synchronized SELF addHeaderIfAbsent(String header, Object value) {
        if (header != null && value != null) {
            safeGetHeader().putIfAbsent(header, value);
        }
        return castSelf();
    }

    private Map<String, Object> safeGetHeader() {
        return headers == null ? headers = new ConcurrentHashMap<>(64) : headers;
    }

    public SELF messageId(String messageId) {
        this.setMessageId(messageId);
        return castSelf();
    }

    @SuppressWarnings("all")
    protected SELF castSelf() {
        return (SELF) this;
    }

    @Override
    public SELF removeHeader(String header) {
        if (this.headers != null) {
            this.headers.remove(header);
        }
        return castSelf();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = JSONUtil.parseObj(this);
        json.set("messageType", getMessageType().name());
        return json;
    }

    @Override
    public Object computeHeader(String key, BiFunction<String, Object, Object> computer) {
        return safeGetHeader().compute(key, computer);
    }

    @Override
    public void fromJson(JSONObject jsonObject) {
        ThingMessage.super.fromJson(jsonObject);
    }

    @Override
    public String toString() {
        return toJson().toString();
    }

    @Override
    @SuppressWarnings("all")
    public SELF copy() {
        return (SELF) ThingMessage.super.copy();
    }
}
