package com.guangjian.hadoken.infra.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.guangjian.hadoken.infra.mybatis.core.handler.DefaultDBFieldHandler;
import org.springframework.context.annotation.Bean;

/**
 * @Author: yanggj
 * @Description: Mybatis 配置类
 * @Date: 2022/02/28 14:55
 * @Version: 1.0.0
 */
public class MybatisAutoConfiguration {

    /**
     * 配置分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();

        // 分页插件
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }

    @Bean
    public MetaObjectHandler defaultMetaObjectHandler(){
        return new DefaultDBFieldHandler(); // 自动填充参数类
    }

}
