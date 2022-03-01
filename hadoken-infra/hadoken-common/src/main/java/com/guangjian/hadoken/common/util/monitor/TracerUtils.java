package com.guangjian.hadoken.common.util.monitor;

import org.apache.skywalking.apm.toolkit.trace.TraceContext;

/**
 * @Author: yanggj
 * @Description: 链路追踪工具类
 * @Date: 2022/02/28 16:06
 * @Version: 1.0.0
 */
public class TracerUtils {

    /**
     * 私有化构造方法
     */
    private TracerUtils() {
    }

    /**
     * 获得链路追踪编号，直接返回 SkyWalking 的 TraceId。
     * 如果不存在的话为空字符串！！！
     *
     * @return 链路追踪编号
     */
    public static String getTraceId() {
        return TraceContext.traceId();
    }

}
