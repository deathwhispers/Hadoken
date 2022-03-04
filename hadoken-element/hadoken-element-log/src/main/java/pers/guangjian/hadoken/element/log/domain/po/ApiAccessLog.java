package pers.guangjian.hadoken.element.log.domain.po;

import lombok.Data;
import pers.guangjian.hadoken.common.enums.UserTypeEnum;
import pers.guangjian.hadoken.common.result.CommonResult;

import javax.persistence.*;
import java.util.Date;

/**
 * API 访问日志
 *
 * @author 芋道源码
 */
@Entity
@Data
@Table(name = "element_api_access_log")
public class ApiAccessLog {

    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 链路追踪编号
     * <p>
     * 一般来说，通过链路追踪编号，可以将访问日志，错误日志，链路追踪日志，logger 打印日志等，结合在一起，从而进行排错。
     */
    private String traceId;
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 用户类型
     * <p>
     * 枚举 {@link UserTypeEnum}
     */
    private Integer userType;
    /**
     * 应用名
     * <p>
     * 目前读取 `spring.application.name` 配置项
     */
    private String applicationName;

    // ========== 请求相关字段 ==========

    /**
     * 请求方法名
     */
    private String requestMethod;
    /**
     * 访问地址
     */
    private String requestUrl;
    /**
     * 请求参数
     * <p>
     * query: Query String
     * body: Quest Body
     */
    private String requestParams;
    /**
     * 用户 IP
     */
    private String userIp;
    /**
     * 浏览器 UA
     */
    private String userAgent;

    // ========== 执行相关字段 ==========

    /**
     * 开始请求时间
     */
    private Date beginTime;
    /**
     * 结束请求时间
     */
    private Date endTime;
    /**
     * 执行时长，单位：毫秒
     */
    private Integer duration;
    /**
     * 结果码
     * <p>
     * 目前使用的 {@link CommonResult#getCode()} 属性
     */
    private Integer resultCode;
    /**
     * 结果提示
     * <p>
     * 目前使用的 {@link CommonResult#getMsg()} 属性
     */
    private String resultMsg;

}
