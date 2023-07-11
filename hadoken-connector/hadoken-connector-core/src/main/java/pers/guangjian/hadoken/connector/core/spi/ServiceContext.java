package pers.guangjian.hadoken.connector.core.spi;

import pers.guangjian.hadoken.connector.core.config.ConfigKey;

import java.util.List;

/**
 * 服务上下文,用于从服务中获取其他服务(如获取spring容器中的bean),配置等操作.
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/10 15:49
 */
public interface ServiceContext {

    <T> T getConfig(ConfigKey<String> key);

    <T> T getConfig(String key);

    <T> T getService(Class<T> service);

    <T> T getService(String service);

    <T> List<T> getServices(Class<T> service);

    <T> List<T> getServices(String service);

}
