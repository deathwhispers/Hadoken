package pers.guangjian.hadoken.tool.codegen.biz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pers.guangjian.hadoken.tool.codegen.biz.domain.GenConfig;

public interface GenConfigRepository extends JpaRepository<GenConfig, Long>, JpaSpecificationExecutor<GenConfig> {

    /**
     * 查询表配置
     *
     * @param tableName 表名
     * @return /
     */
    GenConfig findByTableName(String tableName);
}
