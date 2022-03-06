package pers.guangjian.hadoken.tool.codegen.biz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pers.guangjian.hadoken.tool.codegen.biz.domain.ColumnInfo;

import java.util.List;

public interface ColumnInfoRepository extends JpaRepository<ColumnInfo, Long>, JpaSpecificationExecutor<ColumnInfo> {

    /**
     * 查询表信息
     *
     * @param tableName 表格名
     * @return 表信息
     */
    List<ColumnInfo> findByTableNameOrderByIdAsc(String tableName);
}
