package pers.guangjian.hadoken.connector.core.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 11:19
 */
@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class SimpleConfigKey<V> implements ConfigKey<V> {

    private String key;

    private String name;

    private Class<V> type;
}
