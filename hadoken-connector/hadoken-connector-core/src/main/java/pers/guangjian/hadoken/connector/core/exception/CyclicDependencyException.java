package pers.guangjian.hadoken.connector.core.exception;

import lombok.Getter;

import java.util.Set;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/12 9:48
 */
@Getter
public class CyclicDependencyException extends I18nSupportException {

    private final Set<?> ids;

    public CyclicDependencyException(Set<?> ids) {
        super("error.cyclic_dependence");
        this.ids = ids;
    }

}