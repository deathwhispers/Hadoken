package pers.guangjian.hadoken.connector.core;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/10 16:00
 */
public interface ProtocolSupports {
    boolean isSupport(String protocol);

    Mono<ProtocolSupport> getProtocol(String protocol);

    Flux<ProtocolSupport> getProtocols();
}
