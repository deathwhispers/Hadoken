package pers.guangjian.hadoken.element.log.service;


import pers.guangjian.hadoken.common.entity.PageResult;
import pers.guangjian.hadoken.infra.apilog.core.service.ApiAccessLogFrameworkService;
import pers.guangjian.hadoken.element.log.domain.po.ApiAccessLog;
import pers.guangjian.hadoken.element.log.domain.query.ApiAccessLogExportReqVO;
import pers.guangjian.hadoken.element.log.domain.query.ApiAccessLogPageReqVO;

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
     * @param pageReqVO 分页查询
     * @return API 访问日志分页
     */
    PageResult<ApiAccessLog> getApiAccessLogPage(ApiAccessLogPageReqVO pageReqVO);

    /**
     * 获得 API 访问日志列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return API 访问日志分页
     */
    List<ApiAccessLog> getApiAccessLogList(ApiAccessLogExportReqVO exportReqVO);

}
