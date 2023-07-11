package pers.guangjian.hadoken.connector.core.device;

import pers.guangjian.hadoken.connector.core.things.ThingType;
import pers.guangjian.hadoken.connector.core.things.ThingTypes;
import pers.guangjian.hadoken.connector.core.things.TopicSupport;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 9:35
 */
@AllArgsConstructor
public enum DeviceThingType implements ThingType, TopicSupport {
    device("设备");

    static {
        for (DeviceThingType value : values()) {
            ThingTypes.register(value);
        }
    }
    @Getter
    private final String name;

    @Override
    public String getId() {
        return name();
    }

}