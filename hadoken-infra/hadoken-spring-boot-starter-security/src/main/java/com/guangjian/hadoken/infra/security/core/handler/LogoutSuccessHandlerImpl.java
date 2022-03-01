package com.guangjian.hadoken.infra.security.core.handler;

import cn.hutool.core.util.StrUtil;
import com.guangjian.hadoken.common.result.CommonResult;
import com.guangjian.hadoken.infra.security.config.SecurityProperties;
import com.guangjian.hadoken.infra.security.core.authentication.MultiUserDetailsAuthenticationProvider;
import com.guangjian.hadoken.infra.security.core.util.SecurityUtils;
import com.guangjian.hadoken.infra.web.core.util.ServletUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 自定义退出处理器
 *
 * @author ruoyi
 */
@AllArgsConstructor
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    private final SecurityProperties securityProperties;

    private final MultiUserDetailsAuthenticationProvider authenticationProvider;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 执行退出
        String token = SecurityUtils.obtainAuthorization(request, securityProperties.getTokenHeader());
        if (StrUtil.isNotBlank(token)) {
            authenticationProvider.logout(request, token);
        }
        // 返回成功
        ServletUtils.writeJSON(response, CommonResult.success(null));
    }

}
