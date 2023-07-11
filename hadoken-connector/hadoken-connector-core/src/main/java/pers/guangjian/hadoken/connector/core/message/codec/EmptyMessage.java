package pers.guangjian.hadoken.connector.core.message.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import javax.annotation.Nonnull;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 9:53
 */
public final class EmptyMessage implements EncodedMessage {

    public static final EmptyMessage INSTANCE = new EmptyMessage();

    private EmptyMessage() {
    }

    @Nonnull
    @Override
    public ByteBuf getPayload() {
        return Unpooled.wrappedBuffer(new byte[0]);
    }

    @Override
    public String toString() {
        return "empty message";
    }
}
