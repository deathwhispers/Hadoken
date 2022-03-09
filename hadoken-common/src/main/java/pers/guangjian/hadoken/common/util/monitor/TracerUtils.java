package pers.guangjian.hadoken.common.util.monitor;

import org.apache.skywalking.apm.toolkit.trace.TraceContext;

/**
 * @author yanggj
 *  链路追踪工具类
 * @date 2022/02/28 16:06
 * @version 1.0.0
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
