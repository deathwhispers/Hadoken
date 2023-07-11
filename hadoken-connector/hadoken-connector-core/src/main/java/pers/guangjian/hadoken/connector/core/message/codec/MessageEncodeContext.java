package pers.guangjian.hadoken.connector.core.message.codec;

import pers.guangjian.hadoken.connector.core.device.DeviceOperator;
import pers.guangjian.hadoken.connector.core.message.DeviceMessage;
import pers.guangjian.hadoken.connector.core.message.Message;
import org.reactivestreams.Publisher;
import pers.guangjian.hadoken.connector.core.message.attribute.ReadAttributeMessage;
import pers.guangjian.hadoken.connector.core.message.attribute.WriteAttributeMessage;
import pers.guangjian.hadoken.connector.core.message.firmware.RequestFirmwareMessageResponse;
import pers.guangjian.hadoken.connector.core.message.firmware.UpgradeFirmwareMessage;
import pers.guangjian.hadoken.connector.core.message.function.FunctionInvokeMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * 消息编码上下文,用于平台向设备发送指令并使用协议包进行编码时,可以从上下文中获取一些参数。
 * 通常此接口可强制转换为{@link ToDeviceMessageContext}。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 14:01
 */
public interface MessageEncodeContext extends MessageCodecContext {

    /**
     * 获取平台下发的给设备的消息指令,根据物模型中定义对应不同的消息类型.
     * 在使用时,需要判断对应的类型进行不同的处理
     *
     * @return 消息
     * @see ReadAttributeMessage
     * @see WriteAttributeMessage
     * @see FunctionInvokeMessage
     * @see UpgradeFirmwareMessage
     * @see RequestFirmwareMessageResponse
     * @since 1.0.0
     */
    @Nonnull
    Message getMessage();

    /**
     * 直接回复消息给平台.在类似通过http接入时,下发指令可能是一个同步操作,则可以通过此方法直接回复平台.
     *
     * @param responseMessage 消息流
     * @return void
     * @since 1.0.2
     */
    @Nonnull
    default Mono<Void> response(@Nonnull Publisher<? extends DeviceMessage> responseMessage) {
        return Mono.empty();
    }

    /**
     * {@link MessageEncodeContext#response(Publisher)}
     *
     * @param messages 消息
     * @return void
     * @since 1.1.1
     */
    @Nonnull
    default Mono<Void> response(@Nonnull Collection<? extends DeviceMessage> messages) {
        return response(Flux.fromIterable(messages));
    }

    /**
     * {@link MessageEncodeContext#response(Publisher)}
     *
     * @param messages 消息
     * @return void
     * @since 1.1.1
     */
    @Nonnull
    default Mono<Void> response(@Nonnull DeviceMessage... messages) {
        return response(Flux.fromArray(messages));
    }

    /**
     * 使用新的消息和设备，转换为新上下文
     *
     * @param anotherMessage 设备消息
     * @param device         设备操作接口
     * @return 上下文
     */
    default MessageEncodeContext mutate(Message anotherMessage, DeviceOperator device) {
        return new MessageEncodeContext() {
            @Override
            public Map<String, Object> getConfiguration() {
                return MessageEncodeContext.this.getConfiguration();
            }

            @Override
            public Optional<Object> getConfig(String key) {
                return MessageEncodeContext.this.getConfig(key);
            }

            @Nonnull
            @Override
            public Message getMessage() {
                return anotherMessage;
            }

            @Override
            public Mono<DeviceOperator> getDevice(String deviceId) {
                return MessageEncodeContext.this.getDevice(deviceId);
            }

            @Nullable
            @Override
            public DeviceOperator getDevice() {
                return device;
            }

            @Nonnull
            @Override
            public Mono<Void> response(@Nonnull DeviceMessage... messages) {
                return MessageEncodeContext.this.response(messages);
            }

            @Nonnull
            @Override
            public Mono<Void> response(@Nonnull Collection<? extends DeviceMessage> messages) {
                return MessageEncodeContext.this.response(messages);
            }

            @Nonnull
            @Override
            public Mono<Void> response(@Nonnull Publisher<? extends DeviceMessage> responseMessage) {
                return MessageEncodeContext.this.response(responseMessage);
            }
        };
    }
}
