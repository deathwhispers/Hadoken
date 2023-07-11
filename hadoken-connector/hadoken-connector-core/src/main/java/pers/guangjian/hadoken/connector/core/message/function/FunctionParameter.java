package pers.guangjian.hadoken.connector.core.message.function;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 14:32
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FunctionParameter implements Serializable {

    @Nonnull
    private String name;

    private Object value;

    @Override
    public String toString() {
        return  name+"("+value+")";
    }
}
