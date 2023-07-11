package pers.guangjian.hadoken.connector.core.metadata;

import java.io.Serializable;
import java.util.Map;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/10 16:36
 */
public interface ConfigPropertyMetadata extends ConfigScopeSupport, Serializable {

    String getProperty();

    String getName();

    String getDescription();

    DataType getType();

    Map<String, Object> getExpands();

}
