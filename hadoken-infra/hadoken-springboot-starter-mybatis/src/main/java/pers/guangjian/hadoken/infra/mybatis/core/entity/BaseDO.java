package pers.guangjian.hadoken.infra.mybatis.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体对象
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/02/28 14:58
 */
@Data
public abstract class BaseDO implements Serializable {

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建者，目前使用 SysUser 的 id 编号
     * <p>
     * 使用 String 类型的原因是，未来可能会存在非数值的情况，留好拓展性。
     * </p>
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 更新者，目前使用 SysUser 的 id 编号
     * <p>
     * 使用 String 类型的原因是，未来可能会存在非数值的情况，留好拓展性。
     * </p>
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 是否删除
     */
    @TableLogic
    private Boolean deleted;
}
