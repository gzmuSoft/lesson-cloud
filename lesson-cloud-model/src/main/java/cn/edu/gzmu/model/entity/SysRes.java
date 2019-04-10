package cn.edu.gzmu.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * sys_res
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-10 14:11:01
 */
@Data
@Entity
@Table(name = "sys_res")
@EqualsAndHashCode(callSuper = true)
public class SysRes extends cn.edu.gzmu.model.BaseEntity {

    /**
     * 父权限资源编号
     */
    private java.lang.Long parentId;

    /**
     * 描述
     */
    private java.lang.String des;

    /**
     * url地址
     */
    private java.lang.String url;

    /**
     * 层级
     */
    private java.lang.Integer level;

    /**
     * 图标
     */
    private java.lang.String iconCls;

    /**
     * 类型：1 功能 2 权限
     */
    private java.lang.String type;
}