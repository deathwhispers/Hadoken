package pers.guangjian.hadoken.element.system.modules.auth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pers.guangjian.hadoken.common.enums.UserTypeEnum;
import pers.guangjian.hadoken.common.util.validation.ValidationUtil;
import pers.guangjian.hadoken.element.system.modules.auth.rest.vo.AuthLoginReqVO;
import pers.guangjian.hadoken.element.system.modules.auth.service.AuthService;
import pers.guangjian.hadoken.element.system.modules.auth.service.UserSessionService;
import pers.guangjian.hadoken.element.system.modules.captcha.service.CaptchaService;
import pers.guangjian.hadoken.element.system.modules.common.enums.LoginLogTypeEnum;
import pers.guangjian.hadoken.element.system.modules.common.enums.LoginResultEnum;
import pers.guangjian.hadoken.element.system.modules.user.domain.User;
import pers.guangjian.hadoken.element.system.modules.user.service.UserService;
import pers.guangjian.hadoken.infra.security.core.LoginUser;
import pers.guangjian.hadoken.infra.security.core.authentication.MultiUsernamePasswordAuthenticationToken;
import pers.guangjian.hadoken.infra.web.core.util.ServletUtils;

import javax.annotation.Resource;
import javax.validation.Validator;
import java.util.Objects;

import static pers.guangjian.hadoken.common.exception.util.HadokenServiceExceptionUtil.exception;
import static pers.guangjian.hadoken.element.system.modules.common.enums.ErrorCodeConstants.*;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/4/21 16:21
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    @Lazy // 延迟加载，因为存在相互依赖的问题
    private AuthenticationManager authenticationManager;

    @Resource
    private Validator validator;

    private final CaptchaService captchaService;
    private final UserSessionService userSessionService;
    private final UserService userService;

    @Override
    public String login(AuthLoginReqVO reqVO) {
        // 判断验证码是否正确
        this.verifyCaptcha(reqVO);

        // 使用账号密码，进行登录
        LoginUser loginUser = this.login0(reqVO.getUsername(), reqVO.getPassword());

        // 缓存登陆用户到 Redis 中，返回 sessionId 编号
        return userSessionService.createUserSession(loginUser);
    }

    private LoginUser login0(String username, String password) {
        final LoginLogTypeEnum logTypeEnum = LoginLogTypeEnum.LOGIN_USERNAME;

        // 用户验证
        Authentication authentication;
        try {

            // 调用 Spring Security 的 AuthenticationManager#authenticate(...) 方法，使用账号密码进行认证
            // 在其内部，会调用到 loadUserByUsername 方法，获取 User 信息
            authentication = authenticationManager.authenticate(new MultiUsernamePasswordAuthenticationToken(
                    username, password, getUserType()));
        } catch (BadCredentialsException badCredentialsException) {
            this.createLoginLog(username, logTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        } catch (DisabledException disabledException) {
            this.createLoginLog(username, logTypeEnum, LoginResultEnum.USER_DISABLED);
            throw exception(AUTH_LOGIN_USER_DISABLED);
        } catch (AuthenticationException authenticationException) {
            log.error("[login0][username({}) 发生未知异常]", username, authenticationException);
            this.createLoginLog(username, logTypeEnum, LoginResultEnum.UNKNOWN_ERROR);
            throw exception(AUTH_LOGIN_FAIL_UNKNOWN);
        }

        // 登录成功的日志
        Assert.notNull(authentication.getPrincipal(), "Principal 不会为空");
        this.createLoginLog(username, logTypeEnum, LoginResultEnum.SUCCESS);
        return (LoginUser) authentication.getPrincipal();
    }

    private void verifyCaptcha(AuthLoginReqVO reqVO) {

        // 如果验证码关闭，则不进行校验
        if (!captchaService.isCaptchaEnable()) {
            return;
        }

        // 校验验证码
        ValidationUtil.validate(validator, reqVO, AuthLoginReqVO.CodeEnableGroup.class);

        // 验证码不存在
        final LoginLogTypeEnum logTypeEnum = LoginLogTypeEnum.LOGIN_USERNAME;
        String code = captchaService.getCaptchaCode(reqVO.getUuid());
        if (code == null) {

            // 创建登录失败日志（验证码不存在）
            this.createLoginLog(reqVO.getUsername(), logTypeEnum, LoginResultEnum.CAPTCHA_NOT_FOUND);
            throw exception(AUTH_LOGIN_CAPTCHA_NOT_FOUND);
        }

        // 验证码不正确
        if (!code.equals(reqVO.getCode())) {

            // 创建登录失败日志（验证码不正确)
            this.createLoginLog(reqVO.getUsername(), logTypeEnum, LoginResultEnum.CAPTCHA_CODE_ERROR);
            throw exception(AUTH_LOGIN_CAPTCHA_CODE_ERROR);
        }

        // 正确，所以要删除下验证码
        captchaService.deleteCaptchaCode(reqVO.getUuid());
    }

    private void createLoginLog(String username, LoginLogTypeEnum logTypeEnum, LoginResultEnum loginResult) {

        // 获得用户
        User user = userService.getUserByUsername(username);
        // todo 插入登录日志

        // 更新最后登录时间
        if (user != null && Objects.equals(LoginResultEnum.SUCCESS.getResult(), loginResult.getResult())) {
            userService.updateUserLogin(user.getId(), ServletUtils.getClientIP());
        }
    }

    public UserTypeEnum getUserType() {
        return UserTypeEnum.ADMIN;
    }
}
