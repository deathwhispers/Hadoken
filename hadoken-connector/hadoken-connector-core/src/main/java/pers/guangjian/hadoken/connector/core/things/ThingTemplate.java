package pers.guangjian.hadoken.connector.core.things;

import pers.guangjian.hadoken.connector.core.Configurable;
import pers.guangjian.hadoken.connector.core.Wrapper;

/**
 * 物模版,统一定义物模型等信息
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 9:16
 */
public interface ThingTemplate extends Configurable, Wrapper {

    String getId();

    /**
     * 获取模版物模型
     *
     * @return 物模型
     */
    <T extends ThingMetadata> T getMetadata();

    /**
     * 更新物模型字符串
     *
     * @param metadata 物模型
     * @return true
     */
    Boolean updateMetadata(String metadata);

    /**
     * 更新物模型
     *
     * @param metadata 物模型
     * @return true
     */
    Boolean updateMetadata(ThingMetadata metadata);

}
