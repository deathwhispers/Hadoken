package pers.guangjian.hadoken.common.util.number;

import pers.guangjian.hadoken.common.util.string.StringUtils;

/**
 * @author yanggj
 *  数字的工具类，补全 {@link cn.hutool.core.util.NumberUtil} 的功能
 * @date 2022/02/28 16:07
 * @version 1.0.0
 */
public class NumberUtils {

    public static Long parseLong(String str) {
        return StringUtils.isNotEmpty(str) ? Long.valueOf(str) : null;
    }

}