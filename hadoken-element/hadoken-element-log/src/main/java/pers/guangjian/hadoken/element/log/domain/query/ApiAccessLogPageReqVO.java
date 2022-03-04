package pers.guangjian.hadoken.element.log.domain.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import pers.guangjian.hadoken.common.entity.PageParam;

import java.util.Date;

import static pers.guangjian.hadoken.common.util.date.DateUtil.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


@ApiModel("管理后台 - API 访问日志分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ApiAccessLogPageReqVO extends PageParam {

    @ApiModelProperty(value = "用户编号", example = "666")
    private Long userId;

    @ApiModelProperty(value = "用户类型", example = "2")
    private Integer userType;

    @ApiModelProperty(value = "应用名", example = "dashboard")
    private String applicationName;

    @ApiModelProperty(value = "请求地址", example = "/xxx/yyy", notes = "模糊匹配")
    private String requestUrl;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始开始请求时间")
    private Date beginBeginTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束开始请求时间")
    private Date endBeginTime;

    @ApiModelProperty(value = "执行时长", example = "100", notes = "大于等于，单位：毫秒")
    private Integer duration;

    @ApiModelProperty(value = "结果码", example = "0")
    private Integer resultCode;

}
