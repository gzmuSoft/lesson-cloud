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
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * sys_role
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-8 16:03:14
 */
@Data
@Cacheable
@Table(name = "sys_role")
@Entity(name = "sys_role")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "sys_role", usage = CacheConcurrencyStrategy.READ_WRITE)
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
    @javax.validation.constraints.NotNull(message = "parentId 为必填项")
    private java.lang.Long parentId;
}