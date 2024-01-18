package pers.guangjian.hadoken.component.log.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pers.guangjian.hadoken.common.util.page.PageUtil;
import pers.guangjian.hadoken.common.util.validation.ValidationUtil;
import pers.guangjian.hadoken.component.log.domain.po.ApiErrorLog;
import pers.guangjian.hadoken.component.log.domain.query.ApiErrorLogQueryCriteria;
import pers.guangjian.hadoken.component.log.repository.ApiErrorLogRepository;
import pers.guangjian.hadoken.component.log.service.ApiErrorLogService;
import pers.guangjian.hadoken.component.log.service.mapstruct.ApiErrorLogMapper;
import pers.guangjian.hadoken.infra.apilog.core.service.dto.ApiErrorLogDTO;
import pers.guangjian.hadoken.jpa.QueryHelp;

import java.time.LocalDateTime;
import java.util.List;

/**
 * API 错误日志 Service 实现类
 *
 * @author 芋道源码
 */
@RequiredArgsConstructor
@Service
@Validated
public class ApiErrorLogServiceImpl implements ApiErrorLogService {

    private final ApiErrorLogRepository apiErrorLogRepository;
    private final ApiErrorLogMapper apiErrorLogMapper;


    @Override
    @Async
    public void createApiErrorLogAsync(ApiErrorLogDTO apiErrorLogDTO) {
        apiErrorLogRepository.save(apiErrorLogMapper.toEntity(apiErrorLogDTO));
    }

    @Override
    public Object queryAll(ApiErrorLogQueryCriteria criteria, Pageable pageable) {
        Page<ApiErrorLog> page = apiErrorLogRepository.findAll((((root, query, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder))), pageable);
        return PageUtil.toPage(page.map(apiErrorLogMapper::toDto));
    }

    @Override
    public List<ApiErrorLogDTO> queryAll(ApiErrorLogQueryCriteria criteria) {
        List<ApiErrorLog> logs = apiErrorLogRepository.findAll((((root, query, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder))));
        return apiErrorLogMapper.toDto(logs);
    }

    @Override
    public void updateApiErrorLogProcess(Long id, Integer processStatus, Long processUserId) {
        ApiErrorLog apiErrorLog = apiErrorLogRepository.findById(id).orElseGet(ApiErrorLog::new);
        ValidationUtil.isNull(apiErrorLog.getId(),"ApiErrorLog","id",id);
        apiErrorLog.setProcessStatus(processStatus);
        apiErrorLog.setProcessUserId(processUserId);
        apiErrorLog.setProcessTime(LocalDateTime.now());
        apiErrorLogRepository.save(apiErrorLog);
    }

}
