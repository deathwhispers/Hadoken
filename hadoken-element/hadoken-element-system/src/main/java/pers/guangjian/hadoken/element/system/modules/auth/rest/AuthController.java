package pers.guangjian.hadoken.element.system.modules.auth.rest;

import cn.hutool.core.util.IdUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.guangjian.hadoken.common.result.CommonResult;
import pers.guangjian.hadoken.element.system.modules.auth.rest.vo.AuthLoginReqVO;
import pers.guangjian.hadoken.element.system.modules.auth.rest.vo.AuthLoginRespVO;
import pers.guangjian.hadoken.element.system.modules.auth.service.AuthService;
import pers.guangjian.hadoken.infra.security.core.util.SecurityUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/4/21 16:20
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/code")
@Api(tags = "系统：验证码管理")
public class AuthController {

    private final AuthService authService;


    @ApiOperation("登录授权")
    @PostMapping(value = "/login")
    public CommonResult<Object> login(@Validated @RequestBody AuthLoginReqVO authUser) {

        String token = authService.login(authUser);
        // 返回结果
        return CommonResult.success(AuthLoginRespVO.builder().token(token).build());
    }

    @ApiOperation("获取用户信息")
    @GetMapping(value = "/info")
    public ResponseEntity<Object> getUserInfo() {
        return ResponseEntity.ok(SecurityUtils.getCurrentUser());
    }

    @ApiOperation("获取验证码")
    @GetMapping(value = "/code")
    public ResponseEntity<Object> getCode() {
        // 获取运算的结果
        Captcha captcha = loginProperties.getCaptcha();
        String uuid = properties.getCodeKey() + IdUtil.simpleUUID();
        // 保存
        redisUtils.set(uuid, captcha.text(), loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);
        // 验证码信息
        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};
        return ResponseEntity.ok(imgResult);
    }

    @ApiOperation("退出登录")
    @DeleteMapping(value = "/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        onlineUserService.logout(tokenProvider.getToken(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
