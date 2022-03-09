package pers.guangjian.hadoken.common.exception;

import pers.guangjian.hadoken.common.exception.enums.GlobalErrorCodeConstants;
import pers.guangjian.hadoken.common.exception.enums.ServiceErrorCodeRange;
import lombok.Data;

/**
 * @author yanggj
 *  错误码对象
 * 全局错误码，占用 [0, 999], 参见 {@link GlobalErrorCodeConstants}
 * 业务异常错误码，占用 [1 000 000 000, +∞)，参见 {@link ServiceErrorCodeRange}
 * @date 2022/02/28 15:28
 * @version 1.0.0
 */
@Data
public class ErrorCode {
    /**
     * 错误码
     */
    private final Integer code;
    /**
     * 错误提示
     */
    private final String msg;

    public ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
