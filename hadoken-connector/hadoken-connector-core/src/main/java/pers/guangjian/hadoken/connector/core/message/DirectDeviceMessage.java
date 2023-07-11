package pers.guangjian.hadoken.connector.core.message;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nonnull;

/**
 * 透传消息
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 16:40
 */
@Getter
@Setter
public class DirectDeviceMessage extends CommonDeviceMessage {

    // 原始负载
    @Nonnull
    private byte[] payload;

    @Override
    public MessageType getMessageType() {
        return MessageType.DIRECT;
    }
}
