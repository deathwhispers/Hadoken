package pers.guangjian.hadoken.connector.tcp.server;

import javax.annotation.PreDestroy;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2023/4/13 10:18
 */
public interface ITcpServer {

    /**
     * 主启动程序，初始化参数
     *
     * @throws Exception 初始化异常
     */
    void start() throws Exception;

    /**
     * 优雅的结束服务器
     *
     * @throws InterruptedException 提前中断异常
     */
    @PreDestroy
    void destroy() throws InterruptedException;
}
