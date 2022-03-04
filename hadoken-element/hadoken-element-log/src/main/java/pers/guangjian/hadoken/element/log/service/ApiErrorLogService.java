package pers.guangjian.hadoken.element.log.service;


import pers.guangjian.hadoken.common.entity.PageResult;
import pers.guangjian.hadoken.infra.apilog.core.service.ApiErrorLogFrameworkService;
import pers.guangjian.hadoken.element.log.domain.po.ApiErrorLog;
import pers.guangjian.hadoken.element.log.domain.query.ApiErrorLogExportReqVO;
import pers.guangjian.hadoken.element.log.domain.query.ApiErrorLogPageReqVO;

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
     * @param pageReqVO 分页查询
     * @return API 错误日志分页
     */
    PageResult<ApiErrorLog> getApiErrorLogPage(ApiErrorLogPageReqVO pageReqVO);

    /**
     * 获得 API 错误日志列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return API 错误日志分页
     */
    List<ApiErrorLog> getApiErrorLogList(ApiErrorLogExportReqVO exportReqVO);

    /**
     * 更新 API 错误日志已处理
     *
     * @param id            API 日志编号
     * @param processStatus 处理结果
     * @param processUserId 处理人
     */
    void updateApiErrorLogProcess(Long id, Integer processStatus, Long processUserId);

}
