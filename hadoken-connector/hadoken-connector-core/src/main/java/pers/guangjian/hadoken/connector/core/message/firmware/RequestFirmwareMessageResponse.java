package pers.guangjian.hadoken.connector.core.message.firmware;

import pers.guangjian.hadoken.connector.core.message.CommonDeviceMessageResponse;
import pers.guangjian.hadoken.connector.core.message.MessageType;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 拉取固件更新请求响应
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/18 9:25
 */
@Getter
@Setter
public class RequestFirmwareMessageResponse extends CommonDeviceMessageResponse<RequestFirmwareMessageResponse> {

    /**
     * 固件下载地址
     */
    private String url;

    /**
     * 固件版本
     */
    private String version;

    /**
     * 其他参数
     */
    private Map<String, Object> parameters;

    /**
     * 签名
     */
    private String sign;

    /**
     * 签名方式,md5,sha256
     */
    private String signMethod;

    /**
     * 固件ID
     *
     * @since 1.1.4
     */
    private String firmwareId;

    /**
     * 固件大小
     *
     * @since 1.1.5
     */
    private long size;

    @Override
    public MessageType getMessageType() {
        return MessageType.REQUEST_FIRMWARE_RESPONSE;
    }

}
