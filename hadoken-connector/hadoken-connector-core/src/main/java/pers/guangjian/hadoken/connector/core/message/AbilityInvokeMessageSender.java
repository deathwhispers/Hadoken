package pers.guangjian.hadoken.connector.core.message;

import pers.guangjian.hadoken.connector.core.message.exception.IllegalParameterException;
import pers.guangjian.hadoken.connector.core.message.function.FunctionInvokeMessage;
import pers.guangjian.hadoken.connector.core.message.function.FunctionInvokeMessageResponse;
import pers.guangjian.hadoken.connector.core.message.function.FunctionParameter;
import pers.guangjian.hadoken.connector.core.enums.ErrorCode;
import pers.guangjian.hadoken.connector.core.exception.DeviceOperationException;
import pers.guangjian.hadoken.connector.core.message.exception.FunctionIllegalParameterException;
import pers.guangjian.hadoken.connector.core.message.exception.FunctionUndefinedException;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 调用设备功能的消息发送器
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 14:46
 */
public interface AbilityInvokeMessageSender {
    AbilityInvokeMessageSender custom(Consumer<FunctionInvokeMessage> messageConsumer);

    AbilityInvokeMessageSender header(String header, Object value);

    AbilityInvokeMessageSender addParameter(FunctionParameter parameter);

    AbilityInvokeMessageSender setParameter(List<FunctionParameter> parameter);

    AbilityInvokeMessageSender messageId(String messageId);

    /**
     * 执行参数校验
     * <pre>
     *     function("door-open")
     *     .validate()
     *     .flatMany(FunctionInvokeMessageSender::send)
     *     .doOnError(IllegalParameterException.class,err->log.error(err.getMessage(),err))
     *     ...
     * </pre>
     *
     * @see IllegalParameterException
     * @see FunctionUndefinedException
     * @see FunctionIllegalParameterException
     */
    AbilityInvokeMessageSender validate();

    /**
     * 发送消息
     *
     * @return 返回结果
     * @see DeviceOperationException
     * @see ErrorCode#CLIENT_OFFLINE
     */
    List<FunctionInvokeMessageResponse> send();

    /**
     * 异步发送,并忽略返回结果
     *
     */
    default void sendAndForget() {
        header(Headers.sendAndForget, true)
                .async()
                .send();
    }

    default AbilityInvokeMessageSender accept(Consumer<AbilityInvokeMessageSender> consumer) {
        consumer.accept(this);
        return this;
    }

    default AbilityInvokeMessageSender addParameter(String name, Object value) {
        return addParameter(new FunctionParameter(name, value));
    }

    default AbilityInvokeMessageSender setParameter(Map<String, Object> parameter) {
        parameter.forEach(this::addParameter);
        return this;
    }

    default AbilityInvokeMessageSender timeout(Duration timeout) {
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
    default AbilityInvokeMessageSender async() {
        return this.async(true);
    }

    /**
     * 设置是否异步
     *
     * @param async 是否异步
     * @return this
     * @see AbilityInvokeMessageSender#async(Boolean)
     * @see Headers#async
     */
    default AbilityInvokeMessageSender async(Boolean async) {
        return header(Headers.async, async);
    }

    default <T> AbilityInvokeMessageSender header(HeaderKey<T> header, T value) {
        return header(header.getKey(), value);
    }

    /**
     * 添加多个header到message中
     *
     * @param headers 多个headers
     * @return this
     * @see AbilityInvokeMessageSender#header(String, Object)
     * @see DeviceMessage#addHeader(String, Object)
     * @see Headers
     */
    default AbilityInvokeMessageSender headers(Map<String, Object> headers) {
        Objects.requireNonNull(headers)
                .forEach(this::header);
        return this;
    }

}
