package pers.guangjian.hadoken.connector.core.metadata;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 17:11
 */
public interface Jsonable {
    default JSONObject toJson() {
        return JSONUtil.parseObj(this);
    }

    default void fromJson(JSONObject json) {
        JSONUtil.toBean(json,this.getClass());
    }
}
