package pers.guangjian.hadoken.tool.codegen.biz.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * @Author: yanggj
 * @Description: sql字段转java
 * @Date: 2022/03/02 14:55
 * @Version: 1.0.0
 */
@Slf4j
public class ColUtil {

    /**
     * 转换mysql数据类型为java数据类型
     *
     * @param type 数据库字段类型
     * @return String
     */
    static String cloToJava(String type) {
        Configuration config = getConfig();
        assert config != null;
        return config.getString(type, "unknowType");
    }

    /**
     * 获取配置信息
     */
    public static PropertiesConfiguration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
