package pers.guangjian.hadoken.element.log.domain.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/3/12 23:10
 */
@Entity
@Getter
@Setter
@Table(name = "element_operation_log")
@NoArgsConstructor
public class OperationLog implements Serializable {

    @Id
    @Column(name = "log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 操作用户
     */
    private String username;

    /**
     * 描述
     */
    private String description;

    /**
     * 方法名
     */
    private String method;

    /**
     * 参数
     */
    private String params;

    /**
     * 日志类型
     */
    private String logType;

    /**
     * 请求ip
     */
    private String requestIp;

    /**
     * 地址
     */
    private String address;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 请求耗时
     */
    private Integer duration;

    /**
     * 异常详细
     */
    private byte[] exceptionDetail;

    /**
     * 创建日期
     */
    @CreationTimestamp
    private Timestamp createTime;

    public OperationLog(String logType, Integer duration) {
        this.logType = logType;
        this.duration = duration;
    }
}
