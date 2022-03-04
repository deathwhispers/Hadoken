package pers.guangjian.hadoken.infra.redis.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yanggj
 * @Description: {@link RedisKeyDefinition} 注册表
 * @Date: 2022/03/02 11:08
 * @Version: 1.0.0
 */
public class RedisKeyRegistry {

    /**
     * Redis RedisKeyDefine 数组
     */
    private static final List<RedisKeyDefinition> defines = new ArrayList<>();

    public static void add(RedisKeyDefinition define) {
        defines.add(define);
    }

    public static List<RedisKeyDefinition> list() {
        return defines;
    }

    public static int size() {
        return defines.size();
    }

}
