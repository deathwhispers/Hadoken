package pers.guangjian.hadoken.connector.tcp.message;

import lombok.Data;

/**
 * 捷崇激光雷达车辆计数上报
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2023/4/13 10:52
 */
@Data
public class JiecVehicleNumReport {

    // 心跳命令
    private String cmd;
    // 站点编号
    private String siteNo;
    // 设备id
    private String deviceId;
    // 车辆总数（隧道内当前车辆总数）
    private String vehnum;

}
