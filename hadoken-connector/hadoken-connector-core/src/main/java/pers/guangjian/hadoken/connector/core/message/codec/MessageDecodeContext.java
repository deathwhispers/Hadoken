package pers.guangjian.hadoken.connector.core.message.codec;

import javax.annotation.Nonnull;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 13:56
 */
public interface MessageDecodeContext extends MessageCodecContext {

    /**
     * 获取设备上报的原始消息,根据通信协议的不同,消息类型也不同,
     * 在使用时可能需要转换为对应的消息类型
     *
     * @return 原始消息
     * @see EncodedMessage#getPayload()
     * @see MqttMessage
     * @see HttpExchangeMessage
     * @see CoapExchangeMessage
     * @since 1.0.0
     */
    @Nonnull
    EncodedMessage getMessage();

}
