package pers.guangjian.hadoken.element.log.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pers.guangjian.hadoken.common.util.page.PageUtil;
import pers.guangjian.hadoken.element.log.domain.po.ApiAccessLog;
import pers.guangjian.hadoken.element.log.domain.query.ApiAccessLogQueryCriteria;
import pers.guangjian.hadoken.element.log.repository.ApiAccessLogRepository;
import pers.guangjian.hadoken.element.log.service.ApiAccessLogService;
import pers.guangjian.hadoken.element.log.service.mapstruct.ApiAccessLogMapper;
import pers.guangjian.hadoken.infra.apilog.core.service.dto.ApiAccessLogDTO;
import pers.guangjian.hadoken.jpa.QueryHelp;

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
    public Object queryAll(ApiAccessLogQueryCriteria criteria, Pageable pageable) {
        Page<ApiAccessLog> page = apiAccessLogRepository.findAll(((root, query, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)), pageable);
        return PageUtil.toPage(page.map(apiAccessLogMapper::toDto));
    }

    @Override
    public List<ApiAccessLogDTO> queryAll(ApiAccessLogQueryCriteria criteria) {
        List<ApiAccessLog> logs = apiAccessLogRepository.findAll((root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, criteria, cb));
        return apiAccessLogMapper.toDto(logs);
    }

    @Override
    @Async
    public void createApiAccessLogAsync(ApiAccessLogDTO apiAccessLogDTO) {
        apiAccessLogRepository.save(apiAccessLogMapper.toEntity(apiAccessLogDTO));
    }

    @Override
    public void export(List<ApiAccessLogDTO> logDTOList) {
/*        List<Map<String, Object>> list = new ArrayList<>();
        for (ApiAccessLogDTO log : logDTOList) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("用户名", log.get());
            map.put("IP", log.getRequestIp());
            map.put("IP来源", log.getAddress());
            map.put("描述", log.getDescription());
            map.put("浏览器", log.getBrowser());
            map.put("请求耗时/毫秒", log.getTime());
            map.put("异常详情", new String(ObjectUtil.isNotNull(log.getExceptionDetail()) ? log.getExceptionDetail() : "".getBytes()));
            map.put("创建日期", log.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);*/

    }
}
