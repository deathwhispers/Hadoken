package pers.guangjian.hadoken.connector.core.utils;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 13:50
 */
public class StringUtils {

    /**
     * 编译后的正则表达式缓存
     */
    private static final Map<String, Pattern> PATTERN_CACHE = new ConcurrentHashMap<>();

    /**
     * 对象是否为无效值
     *
     * @param obj 要判断的对象
     * @return 是否为有效值（不为null 和 "" 字符串）
     */
    public static boolean isNullOrEmpty(Object obj) {
        return obj == null || "".equals(obj.toString());
    }

    /**
     * 参数是否是有效数字 （整数或者小数）
     *
     * @param obj 参数（对象将被调用string()转为字符串类型）
     * @return 是否是数字
     */
    public static boolean isNumber(Object obj) {
        if (obj instanceof Number) return true;
        return isInt(obj) || isDouble(obj);
    }

    /**
     * 编译一个正则表达式，并且进行缓存,如果换成已存在则使用缓存
     *
     * @param regex 表达式
     * @return 编译后的Pattern
     */
    public static final Pattern compileRegex(String regex) {
        Pattern pattern = PATTERN_CACHE.get(regex);
        if (pattern == null) {
            pattern = Pattern.compile(regex);
            PATTERN_CACHE.put(regex, pattern);
        }
        return pattern;
    }

    public static String matcherFirst(String patternStr, String text) {
        Pattern pattern = compileRegex(patternStr);
        Matcher matcher = pattern.matcher(text);
        String group = null;
        if (matcher.find()) {
            group = matcher.group();
        }
        return group;
    }

    /**
     * 参数是否是有效整数
     *
     * @param obj 参数（对象将被调用string()转为字符串类型）
     * @return 是否是整数
     */
    public static boolean isInt(Object obj) {
        if (isNullOrEmpty(obj))
            return false;
        if (obj instanceof Integer)
            return true;
        return obj.toString().matches("[-+]?\\d+");
    }

    /**
     * 字符串参数是否是double
     *
     * @param obj 参数（对象将被调用string()转为字符串类型）
     * @return 是否是double
     */
    public static boolean isDouble(Object obj) {
        if (isNullOrEmpty(obj))
            return false;
        if (obj instanceof Double || obj instanceof Float)
            return true;
        return compileRegex("[-+]?\\d+\\.\\d+").matcher(obj.toString()).matches();
    }

    /**
     * 判断一个对象是否为boolean类型,包括字符串中的true和false
     *
     * @param obj 要判断的对象
     * @return 是否是一个boolean类型
     */
    public static boolean isBoolean(Object obj) {
        if (obj instanceof Boolean) return true;
        String strVal = String.valueOf(obj);
        return "true".equalsIgnoreCase(strVal) || "false".equalsIgnoreCase(strVal);
    }

    /**
     * 对象是否为true
     *
     * @param obj /
     * @return /
     */
    public static boolean isTrue(Object obj) {
        return "true".equals(String.valueOf(obj));
    }

    /**
     * 判断一个数组里是否包含指定对象
     *
     * @param arr 对象数组
     * @param obj 要判断的对象
     * @return 是否包含
     */
    public static boolean contains(Object arr[], Object... obj) {
        if (arr == null || obj == null || arr.length == 0) return false;
        return Arrays.asList(arr).containsAll(Arrays.asList(obj));
    }

    /**
     * 将对象转为int值,如果对象无法进行转换,则使用默认值
     *
     * @param object       要转换的对象
     * @param defaultValue 默认值
     * @return 转换后的值
     */
    public static int toInt(Object object, int defaultValue) {
        if (object instanceof Number)
            return ((Number) object).intValue();
        if (isInt(object)) {
            return Integer.parseInt(object.toString());
        }
        if (isDouble(object)) {
            return (int) Double.parseDouble(object.toString());
        }
        return defaultValue;
    }

    /**
     * 将对象转为int值,如果对象不能转为,将返回0
     *
     * @param object 要转换的对象
     * @return 转换后的值
     */
    public static int toInt(Object object) {
        return toInt(object, 0);
    }

    /**
     * 将对象转为long类型,如果对象无法转换,将返回默认值
     *
     * @param object       要转换的对象
     * @param defaultValue 默认值
     * @return 转换后的值
     */
    public static long toLong(Object object, long defaultValue) {
        if (object instanceof Number)
            return ((Number) object).longValue();
        if (isInt(object)) {
            return Long.parseLong(object.toString());
        }
        if (isDouble(object)) {
            return (long) Double.parseDouble(object.toString());
        }
        return defaultValue;
    }

    /**
     * 将对象转为 long值,如果无法转换,则转为0
     *
     * @param object 要转换的对象
     * @return 转换后的值
     */
    public static long toLong(Object object) {
        return toLong(object, 0);
    }

    /**
     * 将对象转为Double,如果对象无法转换,将使用默认值
     *
     * @param object       要转换的对象
     * @param defaultValue 默认值
     * @return 转换后的值
     */
    public static double toDouble(Object object, double defaultValue) {
        if (object instanceof Number)
            return ((Number) object).doubleValue();
        if (isNumber(object)) {
            return Double.parseDouble(object.toString());
        }
        if (null == object) return defaultValue;
        return 0;
    }

    /**
     * 将对象转为Double,如果对象无法转换,将使用默认值0
     *
     * @param object 要转换的对象
     * @return 转换后的值
     */
    public static double toDouble(Object object) {
        return toDouble(object, 0);
    }
}
