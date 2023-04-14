package pers.guangjian.hadoken.connector.tcp.server;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2023/4/13 10:15
 */
@Configuration
@ConfigurationProperties(prefix = TcpServerProperties.PREFIX)
@Data
public class TcpServerProperties {

    public static final String PREFIX = "hadoken.server";

    /**
     * 服务器ip
     */
    private String ip;

    /**
     * 服务器端口
     */
    private Integer port;

    /**
     * 传输模式linux上开启会有更高的性能
     */
    private boolean useEpoll;
}
