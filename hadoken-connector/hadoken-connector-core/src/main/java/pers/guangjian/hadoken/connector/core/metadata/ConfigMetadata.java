package pers.guangjian.hadoken.connector.core.metadata;

import java.io.Serializable;
import java.util.List;

/**
 * 配置信息定义
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/10 16:34
 */
public interface ConfigMetadata extends ConfigScopeSupport, Serializable {

    /**
     * @return 配置名称
     */
    String getName();

    /**
     * @return 配置说明
     */
    String getDescription();

    /**
     * @return 配置属性信息
     */
    List<ConfigPropertyMetadata> getProperties();

    /**
     * 复制为新的配置,并按指定的scope过滤属性,只返回符合scope的属性.
     *
     * @param scopes 范围
     * @return 新的配置
     */
    ConfigMetadata copy(ConfigScope... scopes);
}
