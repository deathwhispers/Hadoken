package pers.guangjian.hadoken.component.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pers.guangjian.hadoken.component.log.domain.po.ApiErrorLog;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/03/04 16:22
 */
public interface ApiErrorLogRepository extends JpaRepository<ApiErrorLog, Long>, JpaSpecificationExecutor<ApiErrorLog> {
}
