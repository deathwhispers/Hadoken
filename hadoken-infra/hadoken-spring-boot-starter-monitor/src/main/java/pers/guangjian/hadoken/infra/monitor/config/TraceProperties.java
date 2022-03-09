package pers.guangjian.hadoken.infra.monitor.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yanggj
 *  BizTracer配置类
 * @date 2022/03/02 10:51
 * @version 1.0.0
 */
@ConfigurationProperties("hadoken.trace")
@Data
public class TraceProperties {
}
