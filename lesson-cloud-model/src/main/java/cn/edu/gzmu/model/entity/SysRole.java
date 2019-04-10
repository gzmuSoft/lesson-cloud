package cn.edu.gzmu.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * sys_role
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-10 14:11:01
 */
@Data
@Entity
@Table(name = "sys_role")
@EqualsAndHashCode(callSuper = true)
public class SysRole extends cn.edu.gzmu.model.BaseEntity {

    /**
     * 描述
     */
    private java.lang.String des;

    /**
     * 图标
     */
    private java.lang.String iconCls;

    /**
     * 父角色编号
     */
    private java.lang.Long parentId;
}