package pers.guangjian.hadoken.connector.core.device.session;

import pers.guangjian.hadoken.connector.core.server.session.DeviceSession;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/12 10:18
 */
@Getter
@Setter
public class DeviceSessionInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String deviceId;

    private String serverId;

    private String address;

    private long connectTime;

    public static DeviceSessionInfo of(String serverId, DeviceSession session) {
        DeviceSessionInfo sessionInfo = new DeviceSessionInfo();
        sessionInfo.setServerId(serverId);
        sessionInfo.setAddress(session.getClientAddress().map(String::valueOf).orElse(null));
        sessionInfo.setConnectTime(session.connectTime());
        sessionInfo.setDeviceId(session.getDeviceId());
        return sessionInfo;
    }
}