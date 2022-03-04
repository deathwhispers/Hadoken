package pers.guangjian.hadoken.tool.codegen.biz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pers.guangjian.hadoken.tool.codegen.biz.domain.GenConfig;

/**
 * @author Zheng Jie
 * @date 2019-01-14
 */
public interface GenConfigRepository extends JpaRepository<GenConfig, Long> {

    /**
     * 查询表配置
     *
     * @param tableName 表名
     * @return /
     */
    GenConfig findByTableName(String tableName);
}
