package com.guangjian.hadoken.infra.security.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: yanggj
 * @Description: 数据范围枚举类
 * @Date: 2022/03/01 18:25
 * @Version: 1.0.0
 */
@Getter
@AllArgsConstructor
public enum DataScopeEnum {

    // 全部数据权限
    ALL(1),

    // 指定组数据权限
    GROUP_CUSTOM(2),

    // 组数据权限
    GROUP_ONLY(3),

    // 组及以下数据权限
    GROUP_AND_CHILD(4),

    // 仅本人数据权限
    SELF(5);

    /**
     * 范围
     */
    private final Integer scope;
}
