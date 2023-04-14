package pers.guangjian.hadoken.connector.tcp.message;

import lombok.Data;

/**
 * 捷崇激光雷达心跳包
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2023/4/13 10:47
 */
@Data
public class JiecHeartBeatResp {

    // 心跳命令
    private String cmd;

    public static JiecHeartBeatResp resp() {
        JiecHeartBeatResp jiecHeartBeatResp = new JiecHeartBeatResp();
        jiecHeartBeatResp.setCmd("1002");
        return jiecHeartBeatResp;
    }
}
