package pers.guangjian.hadoken.infra.apilog.core.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author yanggj
 *  API 错误日志创建 DTO
 * @date 2022/03/02 9:27
 * @version 1.0.0
 */
@ApiModel(value = "API 错误日志创建 DTO", description = "API 错误日志")
@Data
@Accessors(chain = true)
public class ApiAccessLogDTO {

    /**
     * 链路追踪编号
     */
    @ApiModelProperty(value = "链路编号")
    private String traceId;

    /**
     * 用户编号
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
     * 开始请求时间
     */
    @ApiModelProperty(value = "开始请求时间")
    @NotNull(message = "开始请求时间不能为空")
    private Date beginTime;

    /**
     * 结束请求时间
     */
    @ApiModelProperty(value = "结束请求时间")
    @NotNull(message = "结束请求时间不能为空")
    private Date endTime;

    /**
     * 执行时长，单位：毫秒
     */
    @ApiModelProperty(value = "执行时长，单位：毫秒")
    @NotNull(message = "执行时长不能为空")
    private Integer duration;

    /**
     * 结果码
     */
    @ApiModelProperty(value = "结果码")
    @NotNull(message = "结果码不能为空")
    private Integer resultCode;

    /**
     * 结果提示
     */
    @ApiModelProperty(value = "结果提示")
    private String resultMsg;

}
