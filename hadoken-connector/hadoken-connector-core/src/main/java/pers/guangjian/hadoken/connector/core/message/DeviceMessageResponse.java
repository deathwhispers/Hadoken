package pers.guangjian.hadoken.connector.core.message;

import pers.guangjian.hadoken.connector.core.enums.ErrorCode;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 14:40
 */
public interface DeviceMessageResponse extends DeviceMessage, ThingMessageResponse {

    // 是否成功
    boolean isSuccess();

    // 业务码,具体由设备定义
    @Nullable
    String getCode();

    // 错误消息
    @Nullable
    String getMessage();

    // 设置失败
    DeviceMessageResponse error(ErrorCode errorCode);

    // 设置失败
    DeviceMessageResponse error(Throwable err);

    // 设置设备ID
    DeviceMessageResponse deviceId(String deviceId);

    DeviceMessageResponse success();

    DeviceMessageResponse code(@NotNull String code);

    DeviceMessageResponse message(@NotNull String message);

    // 从另外的消息填充对应属性
    DeviceMessageResponse from(@NotNull Message message);

    // 设置消息ID
    DeviceMessageResponse messageId(@NotNull String messageId);

    // 添加头
    @Override
    DeviceMessageResponse addHeader(@NotNull String header, @NotNull Object value);

    @Override
    default DeviceMessageResponse thingId(String type, String thingId) {
        deviceId(thingId);
        return this;
    }

    @Override
    default <T> DeviceMessageResponse addHeader(@NotNull HeaderKey<T> header, @NotNull T value) {
        addHeader(header.getKey(), value);
        return this;
    }

    @Override
    default DeviceMessageResponse copy() {
        return (DeviceMessageResponse) ThingMessageResponse.super.copy();
    }
}
