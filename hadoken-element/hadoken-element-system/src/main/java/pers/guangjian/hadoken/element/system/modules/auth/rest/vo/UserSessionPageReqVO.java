package pers.guangjian.hadoken.element.system.modules.auth.rest.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.guangjian.hadoken.common.entity.PageParam;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/4/22 9:06
 */
@ApiModel("管理后台 - 在线用户 Session 分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserSessionPageReqVO extends PageParam {

    @ApiModelProperty(value = "用户 IP", example = "127.0.0.1", notes = "模糊匹配")
    private String userIp;

    @ApiModelProperty(value = "用户账号", example = "yudao", notes = "模糊匹配")
    private String username;

}
