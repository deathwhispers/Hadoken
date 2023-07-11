package pers.guangjian.hadoken.connector.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 14:51
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
    /* 设备消息相关*/
    REQUEST_HANDLING("请求处理中"),
    CLIENT_OFFLINE("设备未在线"),
    CONNECTION_LOST("设备连接已丢失"),
    NO_RESPONSE("设备未响应"),
    TIME_OUT("超时"),
    UNSUPPORTED_MESSAGE("不支持的操作"),
    PARAMETER_ERROR("参数错误"),
    PARAMETER_UNDEFINED("参数未定义"),
    ABILITY_UNDEFINED("功能未定义"),
    ATTRIBUTE_UNDEFINED("属性未定义"),
    CYCLIC_DEPENDENCE("循环依赖"),
    SERVER_NOT_AVAILABLE("服务不可用"),
    SYSTEM_ERROR("系统错误"),
    UNKNOWN("未知错误");

    private final String text;

    public static Optional<ErrorCode> of(String code) {
        if (code == null) {
            return Optional.empty();
        }
        for (ErrorCode value : values()) {
            if (value.name().equalsIgnoreCase(code)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }

}
