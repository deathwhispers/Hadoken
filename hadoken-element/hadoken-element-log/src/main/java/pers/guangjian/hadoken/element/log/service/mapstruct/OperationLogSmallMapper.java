package pers.guangjian.hadoken.element.log.service.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pers.guangjian.hadoken.element.log.domain.po.OperationLog;
import pers.guangjian.hadoken.element.log.service.dto.OperationLogSmallDTO;
import pers.guangjian.hadoken.jpa.base.BaseMapper;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/3/13 22:29
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OperationLogSmallMapper extends BaseMapper<OperationLogSmallDTO, OperationLog> {
}
