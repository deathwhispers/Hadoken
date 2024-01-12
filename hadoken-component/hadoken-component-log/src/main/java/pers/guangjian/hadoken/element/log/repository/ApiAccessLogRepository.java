package pers.guangjian.hadoken.element.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pers.guangjian.hadoken.element.log.domain.po.ApiAccessLog;

/**
 * @author yanggj
 * @date 2022/03/04 16:22
 * @version 1.0.0
 */
public interface ApiAccessLogRepository extends JpaRepository<ApiAccessLog, Long>, JpaSpecificationExecutor<ApiAccessLog> {
}
