package pers.guangjian.hadoken.infra.mq.core.message;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Author: yanggj
 * @Description: Redis Stream Message 抽象类
 * @Date: 2022/03/02 11:52
 * @Version: 1.0.0
 */
public abstract class AbstractStreamMessage extends AbstractRedisMessage {

    /**
     * 获得 Redis Stream Key
     *
     * @return Channel
     */
    @JsonIgnore
    public abstract String getStreamKey();
}
