package pers.guangjian.hadoken.connector.core.message.interceptor;

import pers.guangjian.hadoken.connector.core.message.codec.EncodedMessage;
import pers.guangjian.hadoken.connector.core.message.codec.InterceptorDeviceMessageCodec;
import pers.guangjian.hadoken.connector.core.message.codec.MessageEncodeContext;
import reactor.core.publisher.Mono;

/**
 * 设备消息解码拦截器,用于在对消息进行编码时进行自定义处理
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 14:25
 * @see MessageEncodeContext
 * @see InterceptorDeviceMessageCodec
 */
public interface DeviceMessageEncodeInterceptor extends DeviceMessageCodecInterceptor {

    /**
     * 编码前执行
     *
     * @param context 编码上下文
     */
    default Mono<Void> preEncode(MessageEncodeContext context) {
        return Mono.empty();
    }

    /**
     * 编码后执行
     *
     * @param context 编码上下文
     * @param message 已编码的消息
     * @return 新的消息
     */
    default Mono<EncodedMessage> postEncode(MessageEncodeContext context, EncodedMessage message) {
        return Mono.just(message);
    }

}
