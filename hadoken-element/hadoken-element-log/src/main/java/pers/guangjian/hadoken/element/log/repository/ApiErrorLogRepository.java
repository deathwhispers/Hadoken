package pers.guangjian.hadoken.element.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pers.guangjian.hadoken.element.log.domain.po.ApiErrorLog;

/**
 * @Author: yanggj
 * @Date: 2022/03/04 16:22
 * @Version: 1.0.0
 */
public interface ApiErrorLogRepository extends JpaRepository<ApiErrorLog, Long> {
}
