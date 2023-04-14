package pers.guangjian.hadoken.connector.tcp.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pers.guangjian.hadoken.connector.tcp.handler.MessageHandler;

import java.util.concurrent.TimeUnit;

/**
 * Netty 通道初始化
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2023/4/13 10:17
 */
@Component
@RequiredArgsConstructor
public class ChannelInit extends ChannelInitializer<SocketChannel> {

    private final MessageHandler messageHandler;

    @Override
    protected void initChannel(SocketChannel channel) {
        channel.pipeline()
                // 心跳时间
                .addLast("idle", new IdleStateHandler(0, 0, 60, TimeUnit.SECONDS))
                // 添加解码器，解析 json 消息，再将消息转为 string 类型，防止出现拆包粘包情形
                .addLast(new JsonObjectDecoder())
                .addLast(new StringDecoder())
                // 添加编码器
                .addLast(new StringEncoder())
                // 添加消息处理器
                .addLast("messageHandler", messageHandler);
    }

}
