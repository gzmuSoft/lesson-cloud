package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;


import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * sys_res
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Data
@Cacheable
@Table(name = "sys_res")
@Entity(name = "sys_res")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "sys_res", usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysRes extends BaseEntity implements Serializable {

    /**
     * 父权限资源编号
     */
    private java.lang.Long parentId;

    /**
     * 描述
     */
    private java.lang.String des;

    /**
     * url 匹配
     */
    private java.lang.String matchUrl;

    /**
     * 路由路径
     */
    private java.lang.String router;

    /**
     * 组件名称
     */
    private java.lang.String component;

    /**
     * 图标
     */
    private java.lang.String iconCls;

    /**
     * 层级
     */
    private java.lang.Integer level;

    /**
     * 允许使用的方法：GET、POST、PUT、PATCH、DELETE、ALL
     */
    private java.lang.String method;

    /**
     * 类型：1 功能 2 权限
     */
    private java.lang.String type;
}