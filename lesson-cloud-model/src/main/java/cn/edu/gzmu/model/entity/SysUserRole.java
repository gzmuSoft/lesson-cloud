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
 * sys_user_role
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-13 22:20:23
 */
@Data
@Cacheable
@Table(name = "sys_user_role")
@Entity(name = "sys_user_role")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "sys_user_role", usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysUserRole extends BaseEntity implements Serializable {

    /**
     * 用户编号
     */
    @javax.validation.constraints.NotNull(message = "userId 为必填项")
    private java.lang.Long userId;

    /**
     * 角色编号
     */
    @javax.validation.constraints.NotNull(message = "roleId 为必填项")
    private java.lang.Long roleId;
}