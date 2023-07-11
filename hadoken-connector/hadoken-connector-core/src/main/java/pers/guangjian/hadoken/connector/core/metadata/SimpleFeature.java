package pers.guangjian.hadoken.connector.core.metadata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/12 9:56
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleFeature implements Feature {
    private String id;
    private String name;
}
