package pers.guangjian.hadoken.connector.core.message.exception;

import pers.guangjian.hadoken.connector.core.exception.I18nSupportException;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 14:53
 */
@Getter
@Setter
public class FunctionUndefinedException extends I18nSupportException {
    private String function;

    public FunctionUndefinedException(String function){
        super("validation.function_undefined",function);
        this.function=function;
    }
}