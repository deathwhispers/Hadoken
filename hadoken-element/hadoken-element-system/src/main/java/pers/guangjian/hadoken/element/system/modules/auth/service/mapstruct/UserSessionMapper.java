package pers.guangjian.hadoken.element.system.modules.auth.service.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/4/22 10:19
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserSessionMapper {
}
