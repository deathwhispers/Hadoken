package com.guangjian.hadoken.common.util.number;

import cn.hutool.core.util.StrUtil;

/**
 * @Author: yanggj
 * @Description: 数字的工具类，补全 {@link cn.hutool.core.util.NumberUtil} 的功能
 * @Date: 2022/02/28 16:07
 * @Version: 1.0.0
 */
public class NumberUtils {

    public static Long parseLong(String str) {
        return StrUtil.isNotEmpty(str) ? Long.valueOf(str) : null;
    }

}