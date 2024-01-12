package pers.guangjian.hadoken.element.log.domain.query;

import lombok.Data;
import pers.guangjian.hadoken.jpa.annotation.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/3/12 23:14
 */
@Data
public class OperationLogQueryCriteria {

    @Query(blurry = "username,description,address,requestIp,method,params")
    private String blurry;

    @Query
    private String logType;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
