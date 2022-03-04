package pers.guangjian.hadoken.element.log.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pers.guangjian.hadoken.common.entity.PageResult;
import pers.guangjian.hadoken.element.log.repository.ApiErrorLogRepository;
import pers.guangjian.hadoken.infra.apilog.core.service.dto.ApiErrorLogCreateReqDTO;
import pers.guangjian.hadoken.element.log.domain.po.ApiErrorLog;
import pers.guangjian.hadoken.element.log.domain.query.ApiErrorLogExportReqVO;
import pers.guangjian.hadoken.element.log.domain.query.ApiErrorLogPageReqVO;
import pers.guangjian.hadoken.element.log.service.ApiErrorLogService;

import javax.annotation.Resource;
import java.util.List;

/**
 * API 错误日志 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ApiErrorLogServiceImpl implements ApiErrorLogService {


    @Override
    public PageResult<ApiErrorLog> getApiErrorLogPage(ApiErrorLogPageReqVO pageReqVO) {
        return null;
    }

    @Override
    public List<ApiErrorLog> getApiErrorLogList(ApiErrorLogExportReqVO exportReqVO) {
        return null;
    }

    @Override
    public void updateApiErrorLogProcess(Long id, Integer processStatus, Long processUserId) {

    }

    @Override
    @Async
    public void createApiErrorLogAsync(ApiErrorLogCreateReqDTO createDTO) {

    }

}
