package pers.guangjian.hadoken.infra.security.core.filter;

import org.springframework.web.filter.OncePerRequestFilter;
import pers.guangjian.hadoken.common.result.CommonResult;
import pers.guangjian.hadoken.common.util.string.StringUtils;
import pers.guangjian.hadoken.infra.security.config.SecurityProperties;
import pers.guangjian.hadoken.infra.security.core.LoginUser;
import pers.guangjian.hadoken.infra.security.core.authentication.MultiUserDetailsAuthenticationProvider;
import pers.guangjian.hadoken.infra.security.core.util.SecurityUtils;
import pers.guangjian.hadoken.infra.web.core.handler.GlobalExceptionHandler;
import pers.guangjian.hadoken.infra.web.core.util.ServletUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT 过滤器，验证 token 的有效性
 * 验证通过后，获得 {@link LoginUser} 信息，并加入到 Spring Security 上下文
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/03/01 18:28
 */
public class JWTAuthenticationTokenFilter extends OncePerRequestFilter {
    private final SecurityProperties securityProperties;

    private final MultiUserDetailsAuthenticationProvider authenticationProvider;

    private final GlobalExceptionHandler globalExceptionHandler;

    public JWTAuthenticationTokenFilter(SecurityProperties securityProperties,
                                        MultiUserDetailsAuthenticationProvider authenticationProvider,
                                        GlobalExceptionHandler globalExceptionHandler) {
        this.securityProperties = securityProperties;
        this.authenticationProvider = authenticationProvider;
        this.globalExceptionHandler = globalExceptionHandler;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = SecurityUtils.obtainAuthorization(request, securityProperties.getTokenHeader());
        if (StringUtils.isNotEmpty(token)) {
            try {

                // 验证 token 有效性
                LoginUser loginUser = authenticationProvider.verifyTokenAndRefresh(request, token);

                // 模拟 Login 功能，方便日常开发调试
                if (loginUser == null) {
                    loginUser = this.mockLoginUser(request, token);
                }

                // 设置当前用户
                if (loginUser != null) {
                    SecurityUtils.setLoginUser(loginUser, request);
                }
            } catch (Throwable ex) {
                CommonResult<?> result = globalExceptionHandler.allExceptionHandler(request, ex);
                ServletUtils.writeJSON(response, result);
                return;
            }
        }

        // 继续过滤链
        chain.doFilter(request, response);
    }

    /**
     * 模拟登录用户，方便日常开发调试
     * <p>
     * 注意，在线上环境下，一定要关闭该功能！！！
     *
     * @param request 请求
     * @param token   模拟的 token，格式为 {@link SecurityProperties#getTokenSecret()} + 用户编号
     * @return 模拟的 LoginUser
     */
    private LoginUser mockLoginUser(HttpServletRequest request, String token) {
        if (!securityProperties.getMockEnable()) {
            return null;
        }

        // 必须以 mockSecret 开头
        if (!token.startsWith(securityProperties.getMockSecret())) {
            return null;
        }
        Long userId = Long.valueOf(token.substring(securityProperties.getMockSecret().length()));
        return authenticationProvider.mockLogin(request, userId);
    }


}
