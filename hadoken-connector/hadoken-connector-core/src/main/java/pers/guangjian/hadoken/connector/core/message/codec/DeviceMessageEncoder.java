package pers.guangjian.hadoken.connector.core.message.codec;

import org.reactivestreams.Publisher;

import javax.annotation.Nonnull;

/**
 * 设备消息编码器,用于将消息对象编码为对应消息协议的消息
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/10 16:09
 */
public interface DeviceMessageEncoder {

    /**
     * 编码,将消息进行编码,用于发送到设备端.
     * <p>
     * 平台在发送指令给设备时,会调用协议包中设置的此方法,将平台消息转为设备能理解的消息.
     * <p>
     * 例如:
     * <pre>
     *
     * //返回单个消息给设备,多个使用Flux&lt;EncodedMessage&gt;作为返回值
     * public Mono&lt;EncodedMessage&gt; encode(MessageEncodeContext context){
     *     return Mono.just(doEncode(context.getMessage()));
     * }
     * </pre>
     *
     * <pre>
     * //忽略发送给设备,直接返回结果给指令发送者
     * public Mono&lt;EncodedMessage&gt; encode(MessageEncodeContext context){
     *    DeviceMessage message = (DeviceMessage)context.getMessage();
     *    return context
     *      .reply(handleMessage(message)) //返回结果给指令发送者
     *      .then(Mono.empty())
     * }
     * </pre>
     * <p>
     *
     * @param context 消息上下文
     * @return 编码结果
     */
    @Nonnull
    Publisher<? extends EncodedMessage> encode(@Nonnull MessageEncodeContext context);

}
