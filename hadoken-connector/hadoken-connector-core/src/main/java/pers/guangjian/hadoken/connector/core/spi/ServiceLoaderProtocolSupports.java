package pers.guangjian.hadoken.connector.core.spi;

import pers.guangjian.hadoken.connector.core.ProtocolSupport;
import pers.guangjian.hadoken.connector.core.ProtocolSupports;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ServiceLoader;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/10 15:54
 */
@Slf4j
public class ServiceLoaderProtocolSupports  implements ProtocolSupports {

    private StaticProtocolSupports supports = new StaticProtocolSupports();

    @Setter
    @NonNull
    private ServiceContext serviceContext;

    @Override
    public boolean isSupport(String protocol) {
        return supports.isSupport(protocol);
    }

    @Override
    public Mono<ProtocolSupport> getProtocol(String protocol) {
        return supports.getProtocol(protocol);
    }

    @Override
    public Flux<ProtocolSupport> getProtocols() {
        return supports.getProtocols();
    }

    protected ClassLoader getClassLoader() {
        return this.getClass().getClassLoader();
    }

    public void init() {
        ServiceLoader<ProtocolSupportProvider> loader = ServiceLoader.load(ProtocolSupportProvider.class, getClassLoader());
        loader.iterator().forEachRemaining(provider -> {

            provider.create(serviceContext)
                    .doOnNext(pro -> log.debug("found protocol support provider:{}", pro))
                    .subscribe(supports::register);
        });
    }
}
