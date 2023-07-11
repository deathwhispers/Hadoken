package pers.guangjian.hadoken.connector.core.message;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/18 10:20
 */
@Getter
@Setter
public class DerivedMetadataMessage extends CommonDeviceMessage {

    // 元数据
    private String metadata;

    // 是否是全量数据
    private boolean all;

    @Override
    public MessageType getMessageType() {
        return MessageType.DERIVED_METADATA;
    }
}