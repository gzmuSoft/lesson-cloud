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
 * sys_user_role
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-11 17:24:41
 */
@Data
@Cacheable
@Table(name = "sys_user_role")
@Entity(name = "sys_user_role")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE )
public class SysUserRole extends BaseEntity {

    /**
     * 用户编号
     */
    private java.lang.Long userId;

    /**
     * 角色编号
     */
    private java.lang.Long roleId;
}