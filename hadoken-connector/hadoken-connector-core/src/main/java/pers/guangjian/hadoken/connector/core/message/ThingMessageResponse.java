package pers.guangjian.hadoken.connector.core.message;

import pers.guangjian.hadoken.connector.core.enums.ErrorCode;
import pers.guangjian.hadoken.connector.core.utils.SerializeUtils;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/12 10:50
 */
public interface ThingMessageResponse extends ThingMessage {

    //是否成功
    boolean isSuccess();

    //业务码,具体由设备定义
    @Nullable
    String getCode();

    //错误消息
    @Nullable
    String getMessage();

    //设置失败
    ThingMessageResponse error(ErrorCode errorCode);

    //设置失败
    ThingMessageResponse error(Throwable err);

    //设置物类型和物ID
    ThingMessageResponse thingId(String type, String thingId);

    //设置成功
    ThingMessageResponse success();

    ThingMessageResponse success(boolean success);

    //设置业务码
    ThingMessageResponse code(@NotNull String code);

    //设置消息
    ThingMessageResponse message(@NotNull String message);

    //根据另外的消息填充对应属性
    ThingMessageResponse from(@NotNull Message message);

    //设置消息ID
    ThingMessageResponse messageId(@NotNull String messageId);

    //设置时间戳
    ThingMessageResponse timestamp(@NotNull long timestamp);

    //添加头
    @Override
    ThingMessageResponse addHeader(@NotNull String header, @NotNull Object value);

    @Override
    default <T> ThingMessageResponse addHeader(@NotNull HeaderKey<T> header, @NotNull T value) {
        addHeader(header.getKey(), value);
        return this;
    }

    @Override
    default ThingMessageResponse copy() {
        return (ThingMessageResponse) ThingMessage.super.copy();
    }

    @Override
    default void writeExternal(ObjectOutput out) throws IOException {
        ThingMessage.super.writeExternal(out);
        out.writeBoolean(isSuccess());
        SerializeUtils.writeNullableUTF(getCode(), out);
        SerializeUtils.writeNullableUTF(getMessage(), out);
    }

    @Override
    default void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        ThingMessage.super.readExternal(in);
        this.success(in.readBoolean());
        this.code(SerializeUtils.readNullableUTF(in));
        this.message(SerializeUtils.readNullableUTF(in));
    }
}
