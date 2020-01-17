package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * sys_role
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-27 10:59:09
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysRole extends BaseEntity implements Serializable {

    /**
     * 描述
     */
    @Size(max = 128, message = "des 不能大于 128 位")
    private java.lang.String des;

    /**
     * 图标
     */
    @Size(max = 55, message = "iconCls 不能大于 55 位")
    private java.lang.String iconCls;

    /**
     * 父角色编号
     */
    @javax.validation.constraints.NotNull(message = "parentId 父角色编号 为必填项")
    private java.lang.Long parentId;
}