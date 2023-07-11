package pers.guangjian.hadoken.connector.core.message.codec;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/10 16:05
 */
public class Transports {

    private static final Map<String, Transport> all = new ConcurrentHashMap<>();

    public static void register(Collection<Transport> transport) {
        transport.forEach(Transports::register);
    }

    public static void register(Transport transport) {
        all.put(transport.getId().toUpperCase(), transport);
    }

    public static List<Transport> get() {
        return new ArrayList<>(all.values());
    }

    public static Optional<Transport> lookup(String id) {
        return Optional.ofNullable(all.get(id.toUpperCase()));
    }

}

