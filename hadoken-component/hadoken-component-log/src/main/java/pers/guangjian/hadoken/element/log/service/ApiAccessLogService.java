package pers.guangjian.hadoken.element.log.service;


import org.springframework.data.domain.Pageable;
import pers.guangjian.hadoken.element.log.domain.query.ApiAccessLogQueryCriteria;
import pers.guangjian.hadoken.infra.apilog.core.service.ApiAccessLogFrameworkService;
import pers.guangjian.hadoken.infra.apilog.core.service.dto.ApiAccessLogDTO;

import java.util.List;

/**
 * API 访问日志 Service 接口
 *
 * @author 芋道源码
 */
public interface ApiAccessLogService extends ApiAccessLogFrameworkService {

    /**
     * 获得 API 访问日志分页
     *
     * @return API 访问日志分页
     */
    Object queryAll(ApiAccessLogQueryCriteria criteria, Pageable pageable);

    /**
     * 获得 API 访问日志列表, 用于 Excel 导出
     *
     * @param criteria 查询条件
     * @return List 访问日志分页
     */
    List<ApiAccessLogDTO> queryAll(ApiAccessLogQueryCriteria criteria);

    /**
     * 导出
     *
     * @param queryAll /
     */
    void export(List<ApiAccessLogDTO> queryAll);
}
