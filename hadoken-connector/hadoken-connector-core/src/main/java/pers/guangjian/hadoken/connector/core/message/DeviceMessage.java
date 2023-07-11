package pers.guangjian.hadoken.connector.core.message;

import pers.guangjian.hadoken.connector.core.device.DeviceThingType;
import pers.guangjian.hadoken.connector.core.metadata.Jsonable;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 14:05
 */
public interface DeviceMessage extends ThingMessage, Jsonable {

    String getDeviceId();

    long getTimestamp();

    default String getThingId() {
        return getDeviceId();
    }

    default String getThingType() {
        return DeviceThingType.device.getId();
    }

    @Override
    default <T> DeviceMessage addHeader(HeaderKey<T> header, T value) {
        ThingMessage.super.addHeader(header, value);
        return this;
    }

    @Override
    DeviceMessage addHeader(String header, Object value);

    @Override
    default <T> DeviceMessage addHeaderIfAbsent(HeaderKey<T> header, T value) {
        ThingMessage.super.addHeaderIfAbsent(header, value);
        return this;
    }

    @Override
    DeviceMessage addHeaderIfAbsent(String header, Object value);

    @Override
    default DeviceMessage copy() {
        return (DeviceMessage) ThingMessage.super.copy();
    }
}
