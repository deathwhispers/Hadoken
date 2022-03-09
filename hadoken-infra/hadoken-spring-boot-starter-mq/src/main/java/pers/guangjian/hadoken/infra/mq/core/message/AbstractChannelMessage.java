package pers.guangjian.hadoken.infra.mq.core.message;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author yanggj
 *  Redis Channel Message 抽象类
 * @date 2022/03/02 11:51
 * @version 1.0.0
 */
public abstract class AbstractChannelMessage extends AbstractRedisMessage {

    /**
     * 获得 Redis Channel
     *
     * @return Channel
     */
    @JsonIgnore
    // 避免序列化。原因是，Redis 发布 Channel 消息的时候，已经会指定。
    public abstract String getChannel();
}
