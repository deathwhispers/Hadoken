package pers.guangjian.hadoken.connector.core.device;

import pers.guangjian.hadoken.connector.core.enums.ErrorCode;
import com.rhy.aibox.connector.core.message.*;
import pers.guangjian.hadoken.connector.core.message.*;
import pers.guangjian.hadoken.connector.core.message.attribute.ReadAttributeMessage;
import pers.guangjian.hadoken.connector.core.message.attribute.ReadAttributeMessageResponse;
import pers.guangjian.hadoken.connector.core.message.attribute.WriteAttributeMessage;
import pers.guangjian.hadoken.connector.core.message.attribute.WriteAttributeMessageResponse;
import pers.guangjian.hadoken.connector.core.message.function.FunctionInvokeMessage;
import pers.guangjian.hadoken.connector.core.message.function.FunctionInvokeMessageResponse;
import pers.guangjian.hadoken.connector.core.message.interceptor.DeviceMessageSenderInterceptor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * 消息发送器,用于发送消息给设备.
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 14:40
 */
public interface DeviceMessageSender {


    /**
     * 发送一个支持回复的消息.
     * <p>
     * ⚠️: 请勿自己实现消息对象,而应该使用框架定义的3种消息.
     * ⚠️: 如果是异步消息,将直接返回<code>{"success":true,"code":"REQUEST_HANDLING"}</code>
     *
     * @param message 具体的消息对象
     * @param <R>     返回类型
     * @return 异步发送结果
     * @see ReadAttributeMessage
     * @see ReadAttributeMessageResponse
     * @see WriteAttributeMessage
     * @see WriteAttributeMessageResponse
     * @see FunctionInvokeMessage
     * @see FunctionInvokeMessageResponse
     * @see ErrorCode#CLIENT_OFFLINE
     * @see ErrorCode#REQUEST_HANDLING
     * @see DeviceMessageSenderInterceptor
     */
    <R extends DeviceMessageResponse> Flux<R> send(Publisher<RepayableDeviceMessage<R>> message);

    /**
     * 发送消息并自定义返回结果转换器
     *
     * @param message      消息
     * @param replyMapping 消息回复转换器
     * @param <R>          回复类型
     * @return 异步发送结果
     * @see DeviceMessageSender#send(Publisher)
     */
    <R extends DeviceMessage> Flux<R> send(Publisher<? extends DeviceMessage> message, Function<Object, R> replyMapping);

    /**
     * 发送消息并获取返回
     *
     * @param message 消息
     * @param <R>     回复类型
     * @return 异步发送结果
     * @see DeviceMessageSender#send(Publisher)
     */
    <R extends DeviceMessage> Flux<R> send(DeviceMessage message);

    /**
     * 发送消息后返回结果,不等待回复
     *
     * @param message 消息
     * @return void
     * @since 1.1.5
     */
    default Mono<Void> sendAndForget(DeviceMessage message) {
        return this
                .send(message.addHeader(Headers.async, true)
                        .addHeader(Headers.sendAndForget, true))
                .then();
    }

    /**
     * 发送{@link FunctionInvokeMessage}消息更便捷的API
     *
     * @param function 要执行的功能
     * @return FunctionInvokeMessageSender
     * @see DeviceMessageSender#send(Publisher)
     * @see FunctionInvokeMessage
     * @see AbilityInvokeMessageSender
     */
    AbilityInvokeMessageSender invokeFunction(String function);

    /**
     * 发送{@link ReadAttributeMessage}消息更便捷的API
     *
     * @param property 要获取的属性列表
     * @return ReadPropertyMessageSender
     * @see DeviceMessageSender#send(Publisher)
     * @see ReadAttributeMessage
     * @see ReadAttributeMessageSender
     */
    ReadAttributeMessageSender readProperty(String... property);

    /**
     * 发送{@link WriteAttributeMessage}消息更便捷的API
     *
     * @return WritePropertyMessageSender
     * @see DeviceMessageSender#send(Publisher)
     * @see WriteAttributeMessage
     * @see WriteAttributeMessageSender
     */
    WriteAttributeMessageSender writeProperty();

}
