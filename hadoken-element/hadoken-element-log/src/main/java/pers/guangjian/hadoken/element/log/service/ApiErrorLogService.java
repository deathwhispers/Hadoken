package pers.guangjian.hadoken.element.log.service;


import org.springframework.data.domain.Pageable;
import pers.guangjian.hadoken.element.log.domain.query.ApiErrorLogQueryCriteria;
import pers.guangjian.hadoken.infra.apilog.core.service.ApiErrorLogFrameworkService;
import pers.guangjian.hadoken.infra.apilog.core.service.dto.ApiErrorLogDTO;

import java.util.List;

/**
 * API 错误日志 Service 接口
 *
 * @author 芋道源码
 */
public interface ApiErrorLogService extends ApiErrorLogFrameworkService {

    /**
     * 获得 API 错误日志分页
     *
     * @return Page 错误日志分页
     */
    Object queryAll(ApiErrorLogQueryCriteria criteria, Pageable pageable);

    /**
     * 获得 API 错误日志列表, 用于 Excel 导出
     *
     * @return List
     */
    List<ApiErrorLogDTO> queryAll(ApiErrorLogQueryCriteria criteria);

    /**
     * 更新 API 错误日志已处理
     *
     * @param id            API 日志编号
     * @param processStatus 处理结果
     * @param processUserId 处理人
     */
    void updateApiErrorLogProcess(Long id, Integer processStatus, Long processUserId);

}
