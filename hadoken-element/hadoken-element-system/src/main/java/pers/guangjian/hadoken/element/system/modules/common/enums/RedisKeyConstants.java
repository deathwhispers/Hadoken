package pers.guangjian.hadoken.element.system.modules.common.enums;

import pers.guangjian.hadoken.infra.redis.core.RedisKeyDefinition;
import pers.guangjian.hadoken.infra.security.core.LoginUser;

import java.time.Duration;

import static pers.guangjian.hadoken.infra.redis.core.RedisKeyDefinition.KeyTypeEnum.STRING;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/4/22 10:00
 */
public interface RedisKeyConstants {

    RedisKeyDefinition CAPTCHA_CODE = new RedisKeyDefinition("验证码的缓存",
            "captcha_code:%s", // 参数为 uuid
            STRING, String.class, RedisKeyDefinition.TimeoutTypeEnum.DYNAMIC);

    RedisKeyDefinition LOGIN_USER = new RedisKeyDefinition("登录用户的缓存",
            "login_user:%s", // 参数为 sessionId
            STRING, LoginUser.class, RedisKeyDefinition.TimeoutTypeEnum.DYNAMIC);


    RedisKeyDefinition SOCIAL_AUTH_STATE = new RedisKeyDefinition("社交登陆的 state", // 注意，它是被 JustAuth 的 justauth.type.prefix 使用到
            "social_auth_state:%s", // 参数为 state
            STRING, String.class, Duration.ofHours(24)); // 值为 state

}
