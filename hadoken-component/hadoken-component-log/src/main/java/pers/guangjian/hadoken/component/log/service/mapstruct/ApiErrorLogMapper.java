package pers.guangjian.hadoken.component.log.service.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pers.guangjian.hadoken.component.log.domain.po.ApiErrorLog;
import pers.guangjian.hadoken.infra.apilog.core.service.dto.ApiErrorLogDTO;
import pers.guangjian.hadoken.jpa.base.BaseMapper;

/**
 * @author yanggj
 * @date 2022/3/10 23:10
 * @version 1.0.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApiErrorLogMapper extends BaseMapper<ApiErrorLogDTO, ApiErrorLog> {
}
