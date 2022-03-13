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
public class OperationLogErrorDTO implements Serializable {

    private Long id;

    private String username;

    private String description;

    private String method;

    private String params;

    private String browser;

    private String requestIp;

    private String address;

    private Timestamp createTime;
}
