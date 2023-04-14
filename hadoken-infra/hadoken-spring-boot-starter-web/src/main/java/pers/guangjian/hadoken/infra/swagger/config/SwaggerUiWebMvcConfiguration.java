package pers.guangjian.hadoken.infra.swagger.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/3/2 20:09
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerUiWebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)

                // 定义是否开启swagger，false为关闭
                //.enable()

                // 将api的元信息设置为包含在json
                .apiInfo(apiInfo())

                // 接口调试地址
                //.host()

                // 选择哪些接口作为swagger的doc发布
                .select()

                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())

                .build();

        // 支持的协议集合
        //.protocols()

        // 授权信息设置,必要的header token等认证信息
        //.securitySchemes()

        // 授权信息全局应用
        //.securityContexts()
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("物联网管理平台")
                .description("")
                .version("1.0.0")
                .build();
    }
}
