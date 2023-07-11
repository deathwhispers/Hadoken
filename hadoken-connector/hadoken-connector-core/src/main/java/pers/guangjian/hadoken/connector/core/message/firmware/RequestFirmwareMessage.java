package pers.guangjian.hadoken.connector.core.message.firmware;

import pers.guangjian.hadoken.connector.core.message.CommonDeviceMessage;
import pers.guangjian.hadoken.connector.core.message.MessageType;
import pers.guangjian.hadoken.connector.core.message.RepayableDeviceMessage;
import lombok.Getter;
import lombok.Setter;

/**
 * 拉取固件更新请求
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 14:13
 */
@Getter
@Setter
public class RequestFirmwareMessage extends CommonDeviceMessage implements RepayableDeviceMessage<RequestFirmwareMessageResponse> {

    //当前设备固件版本,没有则不传
    private String currentVersion;

    //申请更新固件版本,为空则为最新版
    private String requestVersion;

    @Override
    public MessageType getMessageType() {
        return MessageType.REQUEST_FIRMWARE;
    }

    @Override
    public RequestFirmwareMessageResponse newResponse() {
        return new RequestFirmwareMessageResponse().from(this);
    }
}
