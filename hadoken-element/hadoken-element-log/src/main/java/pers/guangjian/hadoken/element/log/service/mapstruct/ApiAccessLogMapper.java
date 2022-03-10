package pers.guangjian.hadoken.element.log.service.mapstruct;

import org.mapstruct.Mapper;
import pers.guangjian.hadoken.element.log.domain.po.ApiAccessLog;
import pers.guangjian.hadoken.infra.apilog.core.service.dto.ApiAccessLogCreateReqDTO;
import pers.guangjian.hadoken.jpa.base.BaseMapper;

/**
 * @Author: yanggj
 * @Date: 2022/3/10 23:07
 * @Version: 1.0.0
 */
//@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Mapper
public interface ApiAccessLogMapper extends BaseMapper<ApiAccessLogCreateReqDTO, ApiAccessLog> {
}
