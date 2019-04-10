package cn.edu.gzmu.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * sys_role_res
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-10 14:11:02
 */
@Data
@Entity
@Table(name = "sys_role_res")
@EqualsAndHashCode(callSuper = true)
public class SysRoleRes extends cn.edu.gzmu.model.BaseEntity {

    /**
     * 角色编号
     */
    private java.lang.Long roleId;

    /**
     * 权限资源编号
     */
    private java.lang.Long resId;
}