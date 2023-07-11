package pers.guangjian.hadoken.connector.core.message.exception;

import lombok.Getter;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 14:51
 */
@Getter
public class IllegalParameterException extends IllegalArgumentException {
    private String parameter;

    public IllegalParameterException(String parameter, String message) {
        super(message);
        this.parameter = parameter;
    }

}
