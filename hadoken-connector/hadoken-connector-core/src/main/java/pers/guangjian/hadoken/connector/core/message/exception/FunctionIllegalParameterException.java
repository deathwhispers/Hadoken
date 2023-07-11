package pers.guangjian.hadoken.connector.core.message.exception;

import lombok.Getter;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 14:50
 */
@Getter
public class FunctionIllegalParameterException extends IllegalParameterException {


    public FunctionIllegalParameterException(String parameter, String message) {

        super(parameter, message);
    }
}