package pers.guangjian.hadoken.element.system.modules.user.service.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/4/22 10:15
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
}
