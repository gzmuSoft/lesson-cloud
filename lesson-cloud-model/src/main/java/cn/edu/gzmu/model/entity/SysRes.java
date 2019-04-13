package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;


import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * sys_res
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-12 20:32:45
 */
@Data
@Cacheable
@Table(name = "sys_res")
@Entity(name = "sys_res")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Cache(region = "sys_res", usage = CacheConcurrencyStrategy.READ_WRITE )
public class SysRes extends BaseEntity {

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