package pers.guangjian.hadoken.element.system.modules.auth.rest.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/4/21 17:39
 */
@ApiModel("管理后台 - 账号密码登录 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthLoginRespVO {
    @ApiModelProperty(value = "token", required = true, example = "yudaoyuanma")
    private String token;
}
