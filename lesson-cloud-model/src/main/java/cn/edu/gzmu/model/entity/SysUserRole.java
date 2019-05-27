package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * sys_user_role
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-27 10:59:09
 */
@Data
@Cacheable
@ToString(callSuper = true)
@Table(name = "sys_user_role")
@Entity(name = "sys_user_role")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "sys_user_role", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class SysUserRole extends BaseEntity implements Serializable {

    /**
     * 用户编号
     */
    @javax.validation.constraints.NotNull(message = "userId 用户编号 为必填项")
    private java.lang.Long userId;

    /**
     * 角色编号
     */
    @javax.validation.constraints.NotNull(message = "roleId 角色编号 为必填项")
    private java.lang.Long roleId;
}