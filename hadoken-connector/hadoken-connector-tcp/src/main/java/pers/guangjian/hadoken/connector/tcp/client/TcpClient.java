package pers.guangjian.hadoken.connector.tcp.client;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pers.guangjian.hadoken.connector.tcp.server.ChannelInit;
import pers.guangjian.hadoken.connector.tcp.server.ITcpServer;
import pers.guangjian.hadoken.connector.tcp.server.TcpServerProperties;

import java.net.InetSocketAddress;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2023/4/10 10:48
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TcpClient implements ITcpServer {

    private final ChannelInit channelInit;
    private final TcpServerProperties serverProperties;

    // 用来接收进来的连接，workerGroup：用来处理已经被接收的连接,进行socketChannel的网络读写，bossGroup接收到连接后就会把连接信息注册到workerGroup
    private EventLoopGroup bossGroup;

    // workerGroup的EventLoopGroup默认的线程数是CPU核数的二倍
    private EventLoopGroup workerGroup;

//    @PostConstruct
    @Override
    public void start() throws Exception {
        log.info("初始化 TCP pers.guangjian.hadoken.connector.tcp.server ...");
        // NioEventLoopGroup 是用来处理I/O操作的Reactor线程组
        bossGroup = serverProperties.isUseEpoll() ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        workerGroup = serverProperties.isUseEpoll() ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        this.tcpClient();
    }

    /**
     * 初始化
     */
    private void tcpClient() {
        try {
            // ServerBootstrap 是一个启动NIO服务的辅助启动类
            ChannelFuture channelFuture = new ServerBootstrap()
                    // 设置group，将bossGroup， workerGroup线程组传递到ServerBootstrap
                    .group(bossGroup, workerGroup)
                    // ServerSocketChannel是以NIO的selector为基础进行实现的，用来接收新的连接，这里告诉Channel通过NioServerSocketChannel获取新的连接
                    .channel(NioServerSocketChannel.class)
                    // 配置编解码器、业务处理、IO
                    .childHandler(channelInit)
                    // tcp 缓冲区
                    // 服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝(队列被接收后，拒绝的客户端下次连接上来只要队列有空余就能连上)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 将网络数据积累到一定的数量后,服务器端才发送出去,会造成一定的延迟。希望服务是低延迟的,建议将TCP_NODELAY设置为true
                    // 立即发送数据，默认值为Ture（Netty默认为True而操作系统默认为False）。
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    // 保持长连接，默认值为False。启用该功能时，TCP会主动探测空闲连接的有效性。
                    // 可以将此功能视为TCP的心跳机制，默认的心跳间隔是7200s即2小时, Netty默认关闭该功能。
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // 绑定端口，开始接收进来的连接
                    .bind("",1);
            if (channelFuture.isSuccess()) {
                log.info("tcpServer启动成功！开始监听端口：{}", serverProperties.getPort());
                // 等待服务器监听端口关闭
                channelFuture.channel().closeFuture().sync();
            } else {
                log.error("tcpServer启动失败！");
            }
        } catch (Exception e) {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        } finally {
            // 退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    @Override
    public void destroy() throws InterruptedException {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

}