package pers.guangjian.hadoken.element.log.domain.query;

import lombok.Data;
import pers.guangjian.hadoken.jpa.annotation.Query;

/**
 * @author yanggj
 * 错误日志查询类
 * @version 1.0.0
 * @date 2022/3/11 22:11
 */
@Data
public class ApiErrorLogQueryCriteria {

    @Query
    private String applicationName;

    @Query(type = Query.Type.LEFT_LIKE)
    private String requestUrl;

    @Query
    private Integer processStatus;
}
