package pers.guangjian.hadoken.infra.monitor.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: yanggj
 * @Description: BizTracer配置类
 * @Date: 2022/03/02 10:51
 * @Version: 1.0.0
 */
@ConfigurationProperties("hadoken.trace")
@Data
public class TraceProperties {
}
