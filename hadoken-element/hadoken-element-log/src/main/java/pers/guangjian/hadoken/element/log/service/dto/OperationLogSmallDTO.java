package pers.guangjian.hadoken.element.log.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/3/13 22:35
 */
@Data
public class OperationLogSmallDTO implements Serializable {

    private String description;

    private String requestIp;

    private Long time;

    private String address;

    private String browser;

    private Timestamp createTime;

}
