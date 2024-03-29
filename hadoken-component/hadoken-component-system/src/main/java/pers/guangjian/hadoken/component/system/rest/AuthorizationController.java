/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package pers.guangjian.hadoken.component.system.rest;

import cn.hutool.core.util.IdUtil;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.guangjian.hadoken.common.config.RsaProperties;
import pers.guangjian.hadoken.common.exception.BadRequestException;
import pers.guangjian.hadoken.common.util.RsaUtils;
import pers.guangjian.hadoken.common.util.string.StringUtils;
import pers.guangjian.hadoken.component.log.annotation.Log;
import pers.guangjian.hadoken.component.security.core.bean.LoginProperties;
import pers.guangjian.hadoken.component.system.service.impl.OnlineUserService;
import pers.guangjian.hadoken.component.system.service.dto.AuthUserDto;
import pers.guangjian.hadoken.component.system.service.dto.JwtUserDto;
import pers.guangjian.hadoken.infra.redis.util.RedisUtils;
import pers.guangjian.hadoken.infra.security.config.SecurityProperties;
import pers.guangjian.hadoken.infra.security.core.util.SecurityUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 * 授权、根据token获取用户详细信息
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Api(tags = "系统：系统授权接口")
public class AuthorizationController {

    private final SecurityProperties properties;
    private final RedisUtils redisUtils;
    private final OnlineUserService onlineUserService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    @Resource
    private LoginProperties loginProperties;

    @Log("用户登录")
    @ApiOperation("登录授权")
    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUserDto authUser, HttpServletRequest request) throws Exception {
        // 密码解密
        String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, authUser.getPassword());
        // 查询验证码
        String code = (String) redisUtils.get(authUser.getUuid());
        // 清除验证码
        redisUtils.del(authUser.getUuid());
        if (StringUtils.isBlank(code)) {
            throw new BadRequestException("验证码不存在或已过期");
        }
        if (StringUtils.isBlank(authUser.getCode()) || !authUser.getCode().equalsIgnoreCase(code)) {
            throw new BadRequestException("验证码错误");
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authUser.getUsername(), password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌
        String token = "";
        final JwtUserDto jwtUserDto = (JwtUserDto) authentication.getPrincipal();
        // 保存在线信息
        onlineUserService.save(jwtUserDto, token, request);
        // 返回 token 与 用户信息
        Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
            put("token", properties.getTokenHeader() + token);
            put("user", jwtUserDto);
        }};
        if (loginProperties.isSingleLogin()) {
            // 踢掉之前已经登录的 token
            onlineUserService.checkLoginOnUser(authUser.getUsername(), token);
        }
        return ResponseEntity.ok(authInfo);
    }

    @ApiOperation("获取用户信息")
    @GetMapping(value = "/info")
    public ResponseEntity<Object> getUserInfo() {
        return ResponseEntity.ok(SecurityUtils.getLoginUser());
    }

    @ApiOperation("获取验证码")
    @GetMapping(value = "/code")
    public ResponseEntity<Object> getCode() {
        // 获取运算的结果
        Captcha captcha = loginProperties.getCaptcha();
        String uuid = properties.getTokenSecret() + IdUtil.simpleUUID();
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
    public ResponseEntity<Object> logout() {
        onlineUserService.logout("");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
