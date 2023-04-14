package pers.guangjian.hadoken.infra.apilog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.guangjian.hadoken.common.enums.WebFilterOrderEnum;
import pers.guangjian.hadoken.infra.apilog.core.filter.ApiAccessLogFilter;
import pers.guangjian.hadoken.infra.apilog.core.service.ApiAccessLogFrameworkService;
import pers.guangjian.hadoken.infra.web.config.HadokenWebAutoConfiguration;
import pers.guangjian.hadoken.infra.web.config.WebProperties;

import javax.servlet.Filter;

/**
 * Api 日志自动配置类
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/03/02 9:19
 */
@Configuration
@AutoConfigureAfter(HadokenWebAutoConfiguration.class)
public class HadokenApiLogAutoConfiguration {

    /**
     * 创建 ApiAccessLogFilter Bean，记录 API 请求日志
     */
    @Bean
    public FilterRegistrationBean<ApiAccessLogFilter> apiAccessLogFilter(WebProperties webProperties,
                                                                         @Value("${spring.application.name}") String applicationName,
                                                                         ApiAccessLogFrameworkService apiAccessLogFrameworkService) {
        ApiAccessLogFilter filter = new ApiAccessLogFilter(webProperties, applicationName, apiAccessLogFrameworkService);
        return createFilterBean(filter, WebFilterOrderEnum.API_ACCESS_LOG_FILTER);
    }

    private static <T extends Filter> FilterRegistrationBean<T> createFilterBean(T filter, Integer order) {
        FilterRegistrationBean<T> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(order);
        return bean;
    }

}
