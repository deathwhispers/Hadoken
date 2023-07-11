package pers.guangjian.hadoken.connector.core.message.codec;

import reactor.core.publisher.Mono;

/**
 * 设备消息转换器,用于对不同协议的消息进行转换
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/10 16:08
 */
public interface DeviceMessageCodec extends DeviceMessageEncoder, DeviceMessageDecoder {

    /**
     * @return 返回支持的传输协议
     */
    Transport getSupportTransport();


    /**
     * 获取协议描述
     * @return 协议描述
     */
    default Mono<? extends MessageCodecDescription> getDescription() {
        return Mono.empty();
    }
}
