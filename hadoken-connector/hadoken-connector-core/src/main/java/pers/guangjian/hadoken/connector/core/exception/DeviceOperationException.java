package pers.guangjian.hadoken.connector.core.exception;

import pers.guangjian.hadoken.connector.core.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/18 9:01
 */
@AllArgsConstructor
public class DeviceOperationException extends RuntimeException {

    @Getter
    private final ErrorCode code;

    private final String message;

    public DeviceOperationException(ErrorCode errorCode) {
        this(errorCode, errorCode.getText());
    }

    public DeviceOperationException(ErrorCode errorCode, Throwable cause) {
        super(cause);
        this.code = errorCode;
        this.message = cause.getMessage();
    }

    @Override
    public String getMessage() {
        return message == null ? code.getText() : message;
    }
}
