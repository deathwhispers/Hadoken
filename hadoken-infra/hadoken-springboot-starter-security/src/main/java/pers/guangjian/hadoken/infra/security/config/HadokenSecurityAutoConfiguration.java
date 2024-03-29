package pers.guangjian.hadoken.infra.security.config;

import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import pers.guangjian.hadoken.infra.security.core.aop.PreAuthenticatedAspect;
import pers.guangjian.hadoken.infra.security.core.authentication.MultiUserDetailsAuthenticationProvider;
import pers.guangjian.hadoken.infra.security.core.context.TransmittableThreadLocalSecurityContextHolderStrategy;
import pers.guangjian.hadoken.infra.security.core.filter.JWTAuthenticationTokenFilter;
import pers.guangjian.hadoken.infra.security.core.handler.AuthenticationEntryPointImpl;
import pers.guangjian.hadoken.infra.security.core.handler.LogoutSuccessHandlerImpl;
import pers.guangjian.hadoken.infra.security.core.service.SecurityAuthFrameworkService;
import pers.guangjian.hadoken.infra.web.config.WebProperties;
import pers.guangjian.hadoken.infra.web.core.handler.GlobalExceptionHandler;

import javax.annotation.Resource;
import java.util.List;

/**
 * Spring Security 自动配置类，主要用于相关组件的配置
 * <p>
 * 注意，不能和 {@link HadokenWebSecurityConfigurerAdapter} 用一个，原因是会导致初始化报错。
 * 参见 https://stackoverflow.com/questions/53847050/spring-boot-delegatebuilder-cannot-be-null-on-autowiring-authenticationmanager 文档。
 *
 * @author yanggj
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(SecurityProperties.class)
public class HadokenSecurityAutoConfiguration {

    @Resource
    private SecurityProperties securityProperties;

    /**
     * 处理用户未登录拦截的切面的 Bean
     */
    @Bean
    public PreAuthenticatedAspect preAuthenticatedAspect() {
        return new PreAuthenticatedAspect();
    }

    /**
     * 认证失败处理类 Bean
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }

    /**
     * 权限不够处理器 Bean
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

    /**
     * 退出处理类 Bean
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(MultiUserDetailsAuthenticationProvider authenticationProvider) {
        return new LogoutSuccessHandlerImpl(securityProperties, authenticationProvider);
    }

    /**
     * Spring Security 加密器
     * 考虑到安全性，这里采用 BCryptPasswordEncoder 加密器
     *
     * @see <a href="http://stackabuse.com/password-encoding-with-spring-security/">Password Encoding with Spring Security</a>
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Token 认证过滤器 Bean
     */
    @Bean
    public JWTAuthenticationTokenFilter authenticationTokenFilter(MultiUserDetailsAuthenticationProvider authenticationProvider,
                                                                  GlobalExceptionHandler globalExceptionHandler) {
        return new JWTAuthenticationTokenFilter(securityProperties, authenticationProvider, globalExceptionHandler);
    }

    /**
     * 身份验证的 Provider Bean，通过它实现账号 + 密码的认证
     */
    @Bean
    public MultiUserDetailsAuthenticationProvider authenticationProvider(List<SecurityAuthFrameworkService> securityFrameworkServices,
                                                                         WebProperties webProperties,
                                                                         PasswordEncoder passwordEncoder) {
        return new MultiUserDetailsAuthenticationProvider(securityFrameworkServices, webProperties, passwordEncoder);
    }

    /**
     * 声明调用 {@link SecurityContextHolder#setStrategyName(String)} 方法，
     * 设置使用 {@link TransmittableThreadLocalSecurityContextHolderStrategy} 作为 Security 的上下文策略
     */
    @Bean
    public MethodInvokingFactoryBean securityContextHolderMethodInvokingFactoryBean() {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setTargetClass(SecurityContextHolder.class);
        methodInvokingFactoryBean.setTargetMethod("setStrategyName");
        methodInvokingFactoryBean.setArguments(TransmittableThreadLocalSecurityContextHolderStrategy.class.getName());
        return methodInvokingFactoryBean;
    }

}
