package pers.guangjian.hadoken.component.log.service.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pers.guangjian.hadoken.component.log.domain.po.OperationLog;
import pers.guangjian.hadoken.component.log.service.dto.OperationLogErrorDTO;
import pers.guangjian.hadoken.jpa.base.BaseMapper;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/3/13 22:29
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OperationLogErrorMapper extends BaseMapper<OperationLogErrorDTO, OperationLog> {
}
