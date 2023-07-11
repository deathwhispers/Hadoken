package pers.guangjian.hadoken.connector.core.device;

import pers.guangjian.hadoken.connector.core.enums.ErrorCode;
import pers.guangjian.hadoken.connector.core.exception.DeviceOperationException;
import pers.guangjian.hadoken.connector.core.message.DeviceMessage;
import pers.guangjian.hadoken.connector.core.message.HeaderKey;
import pers.guangjian.hadoken.connector.core.message.Headers;
import pers.guangjian.hadoken.connector.core.message.attribute.WriteAttributeMessage;
import pers.guangjian.hadoken.connector.core.message.attribute.WriteAttributeMessageResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 修改设备属性消息发送器
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/12 10:24
 */
public interface WriteAttributeMessageSender {

    WriteAttributeMessageSender custom(Consumer<WriteAttributeMessage> messageConsumer);

    WriteAttributeMessageSender header(String header, Object value);

    WriteAttributeMessageSender messageId(String messageId);

    WriteAttributeMessageSender write(String property, Object value);

    Mono<WriteAttributeMessageSender> validate();

    /**
     * 发送消息
     *
     * @return 返回结果
     * @see DeviceOperationException
     * @see ErrorCode#CLIENT_OFFLINE
     */
    Flux<WriteAttributeMessageResponse> send();

    default Mono<Void> sendAndForget() {
        return header(Headers.sendAndForget, true)
                .async()
                .send()
                .then();
    }

    default WriteAttributeMessageSender write(Map<String, Object> properties) {

        properties.forEach(this::write);

        return this;
    }


    default WriteAttributeMessageSender accept(Consumer<WriteAttributeMessageSender> consumer) {
        consumer.accept(this);
        return this;
    }

    default WriteAttributeMessageSender timeout(Duration timeout) {
        return header(Headers.timeout, timeout.toMillis());
    }

    /**
     * 设置调用此功能为异步执行, 当消息发送到设备后,立即返回{@link ErrorCode#REQUEST_HANDLING},而不等待设备返回结果.
     *
     * <code>{"success":true,"code":"REQUEST_HANDLING"}</code>
     *
     * @return this
     * @see Headers#async
     */
    default WriteAttributeMessageSender async() {
        return this.async(true);
    }

    /**
     * 设置是否异步
     *
     * @param async 是否异步
     * @return this
     * @see ReadAttributeMessageSender#async(Boolean)
     * @see Headers#async
     */
    default WriteAttributeMessageSender async(Boolean async) {
        return header(Headers.async, async);
    }

    default <T> WriteAttributeMessageSender header(HeaderKey<T> header, T value) {
        return header(header.getKey(), value);
    }

    /**
     * 添加多个header到message中
     *
     * @param headers 多个headers
     * @return this
     * @see WriteAttributeMessageSender#header(String, Object)
     * @see DeviceMessage#addHeader(String, Object)
     * @see Headers
     */
    default WriteAttributeMessageSender headers(Map<String, Object> headers) {
        Objects.requireNonNull(headers)
                .forEach(this::header);
        return this;
    }

}

