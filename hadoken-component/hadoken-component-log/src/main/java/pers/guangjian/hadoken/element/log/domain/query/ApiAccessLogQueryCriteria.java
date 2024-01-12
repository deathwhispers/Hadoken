package pers.guangjian.hadoken.element.log.domain.query;

import lombok.Data;
import pers.guangjian.hadoken.jpa.annotation.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yanggj
 * 访问日志查询类
 * @version 1.0.0
 * @date 2022/3/11 22:11
 */
@Data
public class ApiAccessLogQueryCriteria {

    @Query
    private String applicationName;

    @Query(type = Query.Type.LEFT_LIKE)
    private String requestUrl;

    @Query(type = Query.Type.BETWEEN)
    private List<LocalDateTime> beginTime;

    @Query
    private Integer resultCode;

}
