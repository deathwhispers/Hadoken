package pers.guangjian.hadoken.connector.core;


/**
 * 设备基类
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/5/24 11:55
 */
public interface M2MDevice extends ResourceOperation {

    // 设备编号
    String getDeviceId();

    // 获取连接
    M2MConnect getConnect();

}
