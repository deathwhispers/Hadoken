package pers.guangjian.hadoken.connector.core;

import pers.guangjian.hadoken.connector.core.request.DownlinkRequest;

/**
 * 资源操作
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/5/24 13:55
 */
public interface ResourceOperation {

    // 读资源，如获取情报板信息，获取设备电压等
    <T, R> T readResource(DownlinkRequest<R> request);

    // 写资源，如发送情报板
    <T, R> T writeResource(DownlinkRequest<R> request);

    // 删除资源
    <T, R> T deleteResource(DownlinkRequest<R> request);

    // 执行资源，如重启设备
    <T, R> T executeResource(DownlinkRequest<R> request);

}
