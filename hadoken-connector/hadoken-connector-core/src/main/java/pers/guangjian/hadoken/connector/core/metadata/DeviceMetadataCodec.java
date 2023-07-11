package pers.guangjian.hadoken.connector.core.metadata;

/**
 * 物模型编解码器,用于将物模型与字符串进行互相转换
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/10 16:53
 */
public interface DeviceMetadataCodec {

    /**
     * @return 物模型标识
     */
    default String getId() {
        return this.getClass().getSimpleName();
    }

    /**
     * @return 物模型名称
     */
    default String getName() {
        return getId();
    }

    /**
     * 将数据解码为物模型
     *
     * @param source 数据
     * @return 物模型
     */
    DeviceMetadata decode(String source);

    /**
     * 将物模型编码为字符串
     *
     * @param metadata 物模型
     * @return 物模型字符串
     */
    String encode(DeviceMetadata metadata);


}
