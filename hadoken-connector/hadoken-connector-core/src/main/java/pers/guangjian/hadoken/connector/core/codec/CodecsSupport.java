package pers.guangjian.hadoken.connector.core.codec;

import org.springframework.core.ResolvableType;

import java.util.Optional;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 10:34
 */
public interface CodecsSupport {
    <T> Optional<Codec<T>> lookup(ResolvableType type);

    int getOrder();
}
