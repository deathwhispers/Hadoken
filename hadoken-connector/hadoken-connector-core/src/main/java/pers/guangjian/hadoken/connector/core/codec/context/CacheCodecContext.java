package pers.guangjian.hadoken.connector.core.codec.context;

import pers.guangjian.hadoken.connector.core.message.DeviceMessageResponse;
import pers.guangjian.hadoken.connector.core.message.RepayableDeviceMessage;
import lombok.AllArgsConstructor;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 9:28
 */
public class CacheCodecContext implements CodecContext {

    private final Map<Object, Cache> caches = new ConcurrentHashMap<>();

    void checkExpires() {
        for (Map.Entry<Object, Cache> entry : caches.entrySet()) {
            if (entry.getValue().isExpired()) {
                caches.remove(entry.getKey());
            }
        }
    }

    @Override
    public void cacheDownstream(Object key, RepayableDeviceMessage<? extends DeviceMessageResponse> message, Duration ttl) {
        caches.put(key, new Cache(System.currentTimeMillis(), ttl.toMillis(), message));
        if (caches.size() > 100) {
            checkExpires();
        }
    }

    @Override
    public <T extends RepayableDeviceMessage<? extends DeviceMessageResponse>> Optional<T> getDownstream(Object key, boolean remove) {
        return Optional
                .ofNullable(remove ? caches.remove(key) : caches.get(key))
                .map(cache -> {
                    if (cache.isAlive()) {
                        return cache.getMessage();
                    }
                    caches.remove(key, cache);
                    return null;
                });
    }


    @AllArgsConstructor
    static class Cache {
        long cacheTime;
        long ttl;
        RepayableDeviceMessage<? extends DeviceMessageResponse> msg;

        boolean isAlive() {
            return !isExpired();
        }

        boolean isExpired() {
            return ttl > 0 && System.currentTimeMillis() - cacheTime >= ttl;
        }

        @SuppressWarnings("all")
        <T extends RepayableDeviceMessage<? extends DeviceMessageResponse>> T getMessage() {
            return (T) msg;
        }
    }
}
