package pers.guangjian.hadoken.element.system.modules.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pers.guangjian.hadoken.common.enums.CommonStatusEnum;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/4/22 9:31
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "element_sys_user")
public class User {

    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 加密后的密码
     *
     */
    private String password;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 备注
     */
    private String remark;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 岗位编号数组
     */
    private Set<Long> postIds;
    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 用户性别
     *
     * 枚举类 {@link SexEnum}
     */
    private Integer sex;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 帐号状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 最后登录IP
     */
    private String loginIp;
    /**
     * 最后登录时间
     */
    private Date loginDate;

}
