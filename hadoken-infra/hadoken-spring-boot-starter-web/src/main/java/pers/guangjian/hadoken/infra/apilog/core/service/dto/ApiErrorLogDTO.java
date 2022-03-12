package pers.guangjian.hadoken.infra.apilog.core.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author yanggj
 * API 错误日志创建 DTO
 * @version 1.0.0
 * @date 2022/03/02 9:27
 */
@ApiModel(value = "API 错误日志创建 DTO", description = "API 错误日志")
@Data
@Accessors(chain = true)
public class ApiErrorLogDTO {

    /**
     * 链路编号
     */
    @ApiModelProperty(value = "链路编号")
    private String traceId;

    /**
     * 账号编号
     */
    @ApiModelProperty(value = "账号编号")
    private Long userId;

    /**
     * 用户类型
     */
    @ApiModelProperty(value = "用户类型")
    private Integer userType;

    /**
     * 应用名
     */
    @ApiModelProperty(value = "应用名")
    @NotNull(message = "应用名不能为空")
    private String applicationName;

    /**
     * 请求方法名
     */
    @ApiModelProperty(value = "请求方法名")
    @NotNull(message = "http 请求方法不能为空")
    private String requestMethod;

    /**
     * 访问地址
     */
    @ApiModelProperty(value = "访问地址")
    @NotNull(message = "访问地址不能为空")
    private String requestUrl;

    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数")
    private String requestParams;

    /**
     * 用户 IP
     */
    @ApiModelProperty(value = "用户 IP")
    @NotNull(message = "ip 不能为空")
    private String userIp;

    /**
     * 浏览器 UA
     */
    @ApiModelProperty(value = "浏览器 UA")
    private String userAgent;

    /**
     * 异常时间
     */
    @ApiModelProperty(value = "异常时间")
    @NotNull(message = "异常时间不能为空")
    private LocalDateTime exceptionTime;

    /**
     * 异常名
     */
    @ApiModelProperty(value = "异常名")
    @NotNull(message = "异常名不能为空")
    private String exceptionName;

    /**
     * 异常发生的类全名
     */
    @ApiModelProperty(value = "异常发生的类全名")
    @NotNull(message = "异常发生的类全名不能为空")
    private String exceptionClassName;

    /**
     * 异常发生的类文件
     */
    @ApiModelProperty(value = "异常发生的类文件")
    @NotNull(message = "异常发生的类文件不能为空")
    private String exceptionFileName;

    /**
     * 异常发生的方法名
     */
    @ApiModelProperty(value = "异常发生的方法名")
    @NotNull(message = "异常发生的方法名不能为空")
    private String exceptionMethodName;

    /**
     * 异常发生的方法所在行
     */
    @ApiModelProperty(value = "异常发生的方法所在行")
    @NotNull(message = "异常发生的方法所在行不能为空")
    private Integer exceptionLineNumber;

    /**
     * 异常的栈轨迹异常的栈轨迹
     */
    @ApiModelProperty(value = "异常的栈轨迹异常的栈轨迹")
    @NotNull(message = "异常的栈轨迹不能为空")
    private String exceptionStackTrace;

    /**
     * 异常导致的根消息
     */
    @ApiModelProperty(value = "异常导致的根消息")
    @NotNull(message = "异常导致的根消息不能为空")
    private String exceptionRootCauseMessage;

    /**
     * 异常导致的消息
     */
    @ApiModelProperty(value = "异常导致的消息")
    @NotNull(message = "异常导致的消息不能为空")
    private String exceptionMessage;

}
