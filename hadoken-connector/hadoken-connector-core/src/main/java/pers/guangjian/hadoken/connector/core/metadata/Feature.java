package pers.guangjian.hadoken.connector.core.metadata;

import pers.guangjian.hadoken.connector.core.device.DeviceFeatures;
import pers.guangjian.hadoken.connector.core.message.codec.CodecFeature;

/**
 * 特性接口,一般使用枚举实现。用于定义协议或者设备支持的某些特性.
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/12 9:55
 * @see MetadataFeature
 * @see ManagementFeature
 * @see SimpleFeature
 * @see CodecFeature
 * @see DeviceFeatures
 */
public interface Feature {
    /**
     * @return 唯一标识
     */
    String getId();

    /**
     * @return 名称
     */
    String getName();

    /**
     * 特性类型,用于进行分类,比如: 协议特性等
     *
     * @return 类型
     * @since 2.0
     */
    default String getType() {
        return getClass().getSimpleName();
    }

    /**
     * @return 是否已经被弃用
     * @see Deprecated
     * @since 2.0
     */
    default boolean isDeprecated() {
        return null != getClass().getAnnotation(Deprecated.class);
    }

    static Feature of(String id, String name) {
        return new SimpleFeature(id, name);
    }

}