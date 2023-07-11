package pers.guangjian.hadoken.connector.core.message;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/18 10:15
 */
@Getter
@Setter
public class DefaultBroadcastMessage implements BroadcastMessage {
    private static final long serialVersionUID = -6849794470754667710L;

    private String messageId;

    private long timestamp = System.currentTimeMillis();

    private String address;

    private Message message;

    private Map<String, Object> headers;

    @Nullable
    @Override
    public Map<String, Object> getHeaders() {
        return headers;
    }

    private Map<String, Object> safeGetHeader() {
        return headers == null ? headers = new ConcurrentHashMap<>() : headers;
    }

    @Override
    public synchronized BroadcastMessage addHeader(String header, Object value) {
        if (StringUtils.isEmpty(header) || StringUtils.isEmpty(value)) {
            return this;
        }
        safeGetHeader().put(header, value);
        return this;
    }

    @Override
    public synchronized BroadcastMessage addHeaderIfAbsent(String header, Object value) {
        if (StringUtils.isEmpty(header) || StringUtils.isEmpty(value)) {
            return this;
        }
        safeGetHeader().putIfAbsent(header, value);
        return this;
    }

    @Override
    public synchronized BroadcastMessage removeHeader(String header) {
        if (StringUtils.isEmpty(header)) {
            return this;
        }
        safeGetHeader().remove(header);
        return this;
    }

    @Override
    public Object computeHeader(String key, BiFunction<String, Object, Object> computer) {
        return safeGetHeader().compute(key, computer);
    }

    @Override
    public BroadcastMessage message(Message message) {
        this.message = message;
        return this;
    }

    @Override
    public BroadcastMessage address(String address) {
        this.address = address;
        return this;
    }
}
