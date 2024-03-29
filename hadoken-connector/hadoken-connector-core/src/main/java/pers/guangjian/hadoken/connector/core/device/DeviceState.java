package pers.guangjian.hadoken.connector.core.device;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 9:39
 */
public interface DeviceState {

    //未知
    byte unknown = 0;

    //在线
    byte online = 1;

    //未激活
    byte noActive = -3;

    //离线
    byte offline = -1;

    //检查状态超时
    byte timeout = -2;

}
