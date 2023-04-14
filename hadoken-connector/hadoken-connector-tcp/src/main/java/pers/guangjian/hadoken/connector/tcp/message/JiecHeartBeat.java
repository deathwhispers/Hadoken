package pers.guangjian.hadoken.connector.tcp.message;

import lombok.Data;

import java.util.List;

/**
 * 捷崇激光雷达心跳包
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2023/4/13 10:47
 */
@Data
public class JiecHeartBeat {

    // 心跳命令
    private String cmd;
    // 站点编号
    private String siteno;
    // 设备id
    private String deviceid;
    // 设备列表
    private List<DeviceStatus> devicelist;

}

@Data
class DeviceStatus {

    // 设备状态（DISCONNECT：未连接，DEVICEERROR：设备故障，HEALTHY：设备正常）
    private String state;
    // 设备类型（ENTER：入口设备，EXIT：出口设备）
    private String type;

}
