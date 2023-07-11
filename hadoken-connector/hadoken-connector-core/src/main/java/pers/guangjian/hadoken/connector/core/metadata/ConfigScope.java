package pers.guangjian.hadoken.connector.core.metadata;

/**
 * 配置作用域,请使用枚举实现此接口
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/10 16:35
 */
public interface ConfigScope {
    String getId();

    default String getName() {
        return getId();
    }

    static ConfigScope of(String id) {
        return () -> id;
    }
}
