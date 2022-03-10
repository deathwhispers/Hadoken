package pers.guangjian.hadoken.element.log.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pers.guangjian.hadoken.common.entity.PageResult;
import pers.guangjian.hadoken.element.log.domain.po.ApiAccessLog;
import pers.guangjian.hadoken.element.log.domain.query.ApiAccessLogExportReqVO;
import pers.guangjian.hadoken.element.log.domain.query.ApiAccessLogPageReqVO;
import pers.guangjian.hadoken.element.log.repository.ApiAccessLogRepository;
import pers.guangjian.hadoken.element.log.service.ApiAccessLogService;
import pers.guangjian.hadoken.element.log.service.mapstruct.ApiAccessLogMapper;
import pers.guangjian.hadoken.infra.apilog.core.service.dto.ApiAccessLogCreateReqDTO;

import java.util.List;

/**
 * API 访问日志 Service 实现类
 *
 * @author 芋道源码
 */
@RequiredArgsConstructor
@Service
@Validated
public class ApiAccessLogServiceImpl implements ApiAccessLogService {

    private final ApiAccessLogRepository apiAccessLogRepository;
    private final ApiAccessLogMapper apiAccessLogMapper;

    @Override
    public PageResult<ApiAccessLog> getApiAccessLogPage(ApiAccessLogPageReqVO pageReqVO) {
        return null;
    }

    @Override
    public List<ApiAccessLog> getApiAccessLogList(ApiAccessLogExportReqVO exportReqVO) {
        return null;
    }

    @Override
    @Async
    public void createApiAccessLogAsync(ApiAccessLogCreateReqDTO createDTO) {
        apiAccessLogRepository.save(apiAccessLogMapper.toEntity(createDTO));
    }

}
