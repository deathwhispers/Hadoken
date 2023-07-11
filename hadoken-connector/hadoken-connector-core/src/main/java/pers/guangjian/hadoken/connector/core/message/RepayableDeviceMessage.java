package pers.guangjian.hadoken.connector.core.message;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/18 9:05
 */
public interface RepayableDeviceMessage<R extends DeviceMessageResponse> extends DeviceMessage {

    /**
     * 新建一个回复对象
     *
     * @return 回复对象
     */
    R newResponse();

}
