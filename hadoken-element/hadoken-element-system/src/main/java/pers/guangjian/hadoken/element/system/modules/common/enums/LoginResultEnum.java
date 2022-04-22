package pers.guangjian.hadoken.element.system.modules.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/4/22 9:12
 */
@Getter
@AllArgsConstructor
public enum LoginResultEnum {

    // 成功
    SUCCESS(0),
    // 账号或密码不正确
    BAD_CREDENTIALS(10),
    // 用户被禁用
    USER_DISABLED(20),
    // 图片验证码不存在
    CAPTCHA_NOT_FOUND(30),
    // 图片验证码不正确
    CAPTCHA_CODE_ERROR(31),

    // 未知异常
    UNKNOWN_ERROR(100),
    ;

    /**
     * 结果
     */
    private final Integer result;

}
