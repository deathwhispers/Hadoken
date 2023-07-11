package pers.guangjian.hadoken.connector.core.message.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 14:53
 */
@Getter
@Setter
public class ParameterUndefinedException extends IllegalArgumentException {
    private String parameter;

    public ParameterUndefinedException(String parameter, String message){
        super(message);
        this.parameter=parameter;
    }
}
