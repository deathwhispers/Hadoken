package pers.guangjian.hadoken.connector.core.request;

import pers.guangjian.hadoken.connector.core.RequestType;
import lombok.Data;

/**
 * 下行请求
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/5/24 13:43
 */
@Data
public class DownlinkRequest<T> {

    private String deviceId;

    private String traceId;

    //
    private RequestType requestType;

    private String resourceType;

    private T payload;

    /**
     * <code>true</code>直接下发
     * <p>
     * <code>false</code>缓存下发（可能设备离线无法送达）
     */
    private Boolean direct;

}
