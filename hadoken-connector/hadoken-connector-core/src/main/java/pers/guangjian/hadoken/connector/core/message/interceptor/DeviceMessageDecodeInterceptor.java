package pers.guangjian.hadoken.connector.core.message.interceptor;

import pers.guangjian.hadoken.connector.core.message.Message;
import pers.guangjian.hadoken.connector.core.message.codec.MessageDecodeContext;
import reactor.core.publisher.Mono;

/**
 * 设备消息解码拦截器
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 14:25
 */
public interface DeviceMessageDecodeInterceptor {

    /**
     * 解码前执行
     *
     * @param context 上下文
     */
    default Mono<Void> preDecode(MessageDecodeContext context){
        return Mono.empty();
    }

    /**
     * 解码后执行
     *
     * @param context       消息上下文
     * @param deviceMessage 解码后的设备消息
     * @return 新的设备消息
     */
    default <T extends Message,R extends T> Mono<T> postDecode(MessageDecodeContext context, R deviceMessage){
        return Mono.empty();
    }

}

