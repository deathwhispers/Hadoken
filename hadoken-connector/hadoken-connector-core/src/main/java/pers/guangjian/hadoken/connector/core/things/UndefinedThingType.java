package pers.guangjian.hadoken.connector.core.things;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 9:33
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class UndefinedThingType implements ThingType {
    private final String id;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ThingType)) {
            return false;
        }
        ThingType that = (ThingType) o;
        return id != null ? id.equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
