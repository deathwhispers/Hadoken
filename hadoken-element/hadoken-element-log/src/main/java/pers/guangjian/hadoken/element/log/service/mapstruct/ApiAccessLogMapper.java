package pers.guangjian.hadoken.element.log.service.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pers.guangjian.hadoken.element.log.domain.po.ApiAccessLog;
import pers.guangjian.hadoken.element.log.domain.query.ApiAccessLogRespVO;
import pers.guangjian.hadoken.infra.apilog.core.service.dto.ApiAccessLogDTO;
import pers.guangjian.hadoken.jpa.base.BaseMapper;

import java.util.List;

/**
 * @author yanggj
 * @date 2022/3/10 23:07
 * @version 1.0.0
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApiAccessLogMapper extends BaseMapper<ApiAccessLogDTO, ApiAccessLog> {

    List<ApiAccessLogRespVO> toRespList(List<ApiAccessLog> dtoList);

}
