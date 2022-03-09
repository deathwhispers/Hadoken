package pers.guangjian.hadoken.common.enums;

import lombok.Getter;

/**
 * @author yanggj
 *  通用状态枚举
 * @date 2022/02/28 15:51
 * @version 1.0.0
 */
@Getter
public enum CommonStatusEnum {
    ENABLE(0, "开启"),
    DISABLE(1, "关闭");

    /**
     * 状态值
     */
    private final Integer status;

    /**
     * 状态名
     */
    private final String name;

    CommonStatusEnum(Integer status, String name) {
        this.status = status;
        this.name = name;
    }
}
