package pers.guangjian.hadoken.connector.core.server;

import pers.guangjian.hadoken.connector.core.message.codec.EncodedMessage;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * 客户端连接
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 9:50
 */
public interface ClientConnection {

    /**
     * @return 客户端地址
     */
    InetSocketAddress address();

    /**
     * 发送消息给客户端
     *
     * @param message 消息
     * @return void
     */
    void sendMessage(EncodedMessage message);

    /**
     * 接收来自客户端消息
     *
     * @return 消息流
     */
    List<EncodedMessage> receiveMessage();

    /**
     * 断开连接
     */
    void disconnect();

    /**
     * 连接是否还存活
     */
    boolean isAlive();
}
