package pers.guangjian.hadoken.connector.core.message.codec;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 9:54
 */
public enum MessagePayloadType {

    JSON, STRING, BINARY, HEX, UNKNOWN;

    public static MessagePayloadType of(String of) {
        for (MessagePayloadType value : MessagePayloadType.values()) {
            if (value.name().equalsIgnoreCase(of)) {
                return value;
            }
        }
        return UNKNOWN;
    }
}
