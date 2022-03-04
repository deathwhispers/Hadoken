package pers.guangjian.hadoken.infra.security.config;

import org.springframework.core.Ordered;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import pers.guangjian.hadoken.infra.web.config.WebProperties;

import javax.annotation.Resource;

/**
 * @Author: yanggj
 * @Description: 自定义的 URL 的安全配置
 * 目的：每个 Maven Module 可以自定义规则！
 * @Date: 2022/03/02 9:38
 * @Version: 1.0.0
 */
public abstract class AuthorizeRequestsCustomizer
        implements Customizer<ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry>, Ordered {

    @Resource
    private WebProperties webProperties;

    protected String buildAdminApi(String url) {
        return webProperties.getAdminApi().getPrefix() + url;
    }

    protected String buildAppApi(String url) {
        return webProperties.getAppApi().getPrefix() + url;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
