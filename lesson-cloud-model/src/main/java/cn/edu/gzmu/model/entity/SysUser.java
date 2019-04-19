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
 * sys_user
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-19 22:08:04
 */
@Data
@Cacheable
@Table(name = "sys_user")
@Entity(name = "sys_user")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Cache(region = "sys_user", usage = CacheConcurrencyStrategy.READ_WRITE )
public class SysUser extends BaseEntity {

    /**
     * 用户主体编号
     */
    private java.lang.Long entityId;

    /**
     * 0：系统管理员、1：教务管理员、2：课程管理员、3：教师、4：学生
     */
    private java.lang.Integer entityType;

    /**
     * 用于用户密码的加盐处理
     */
    private java.lang.String salt;

    /**
     * 密码
     */
    private java.lang.String pwd;

    /**
     * 1：正常、2：锁定一小时、3：禁用
     */
    private java.lang.Integer status;

    /**
     * 
     */
    private java.lang.String icon;

    /**
     * 电子邮箱
     */
    private java.lang.String email;

    /**
     * 联系电话
     */
    private java.lang.String phone;

    /**
     * 在线状态  1-在线 0-离线
     */
    private java.lang.Boolean onlineStatus;
}