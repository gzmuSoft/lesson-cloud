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
 * sys_user
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-13 22:20:23
 */
@Data
@Cacheable
@Table(name = "sys_user")
@Entity(name = "sys_user")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "sys_user", usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysUser extends BaseEntity implements Serializable {

    /**
     * 用户主体编号
     */
    private java.lang.Long entityId;

    /**
     * 0：系统管理员、1：教务管理员、2：课程管理员、3：教师、4：学生
     */
    private java.lang.Integer entityType;

    /**
     * 密码
     */
    @Size(max = 255, message = "pwd 不能大于 255 位")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private java.lang.String pwd;

    /**
     * 1：正常、2：锁定一小时、3：禁用
     */
    @javax.validation.constraints.NotNull(message = "status 为必填项")
    private java.lang.Integer status;

    /**
     * 图标
     */
    @Size(max = 255, message = "icon 不能大于 255 位")
    private java.lang.String icon;

    /**
     * 电子邮箱
     */
    @Size(max = 255, message = "email 不能大于 255 位")
    @javax.validation.constraints.Email(message = "email不合法，请输入正确的邮箱地址")
    private java.lang.String email;

    /**
     * 联系电话
     */
    @javax.validation.constraints.NotNull(message = "phone 为必填项")
    @Size(max = 20, message = "phone 不能大于 20 位")
    private java.lang.String phone;

    /**
     * 在线状态  1-在线 0-离线
     */
    private java.lang.Boolean onlineStatus;
}