package pers.guangjian.hadoken.connector.core.things;

/**
 * 支持 Topic 的物类型
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 9:33
 */
public interface TopicSupport {

    /**
     * 根据物类型,获取topic前缀
     *
     * @param templateId 模版ID
     * @param thingId    物ID
     * @return topic前缀
     */
    String getTopicPrefix(String templateId, String thingId);

}
