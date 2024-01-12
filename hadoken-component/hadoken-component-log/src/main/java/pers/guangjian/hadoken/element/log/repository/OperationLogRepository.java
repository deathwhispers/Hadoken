package pers.guangjian.hadoken.element.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pers.guangjian.hadoken.element.log.domain.po.OperationLog;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/3/12 23:12
 */
public interface OperationLogRepository extends JpaRepository<OperationLog, Long>, JpaSpecificationExecutor<OperationLog> {

    /**
     * 根据日志类型删除信息
     *
     * @param logType 日志类型
     */
    @Modifying
    @Query(value = "delete from component_operation_log where log_type = ?1", nativeQuery = true)
    void deleteByLogType(String logType);
}
