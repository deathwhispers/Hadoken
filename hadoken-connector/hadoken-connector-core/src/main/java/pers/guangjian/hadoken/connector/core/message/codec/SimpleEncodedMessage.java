package pers.guangjian.hadoken.connector.core.message.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.charset.StandardCharsets;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 9:54
 */
@Getter
@AllArgsConstructor
public class SimpleEncodedMessage implements EncodedMessage {

    private final ByteBuf payload;

    private final MessagePayloadType payloadType;

    public static SimpleEncodedMessage of(ByteBuf byteBuf, MessagePayloadType payloadType) {
        return new SimpleEncodedMessage(byteBuf, payloadType);
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        if (ByteBufUtil.isText(payload, StandardCharsets.UTF_8)) {
            builder.append(payload.toString(StandardCharsets.UTF_8));
        } else {
            ByteBufUtil.appendPrettyHexDump(builder, payload);
        }
        return builder.toString();
    }
}
