package cn.edu.gzmu.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * sys_user_role
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-10 14:11:02
 */
@Data
@Entity
@Table(name = "sys_user_role")
@EqualsAndHashCode(callSuper = true)
public class SysUserRole extends cn.edu.gzmu.model.BaseEntity {

    /**
     * 用户编号
     */
    private java.lang.Long userId;

    /**
     * 角色编号
     */
    private java.lang.Long roleId;
}