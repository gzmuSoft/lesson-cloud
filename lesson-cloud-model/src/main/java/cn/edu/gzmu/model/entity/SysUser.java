package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Cacheable;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 * sys_user
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-27 10:59:09
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysUser extends BaseEntity {

    /**
     * 密码
     */
    @javax.validation.constraints.NotNull(message = "pwd 密码 为必填项")
    @Size(max = 255, message = "pwd 不能大于 255 位")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private java.lang.String pwd;

    /**
     * 1：正常、2：锁定一小时、3：禁用
     */
    @javax.validation.constraints.NotNull(message = "status 1：正常、2：锁定一小时、3：禁用 为必填项")
    private java.lang.Integer status;

    /**
     * 图标
     */
    @Size(max = 255, message = "icon 不能大于 255 位")
    private java.lang.String icon;

    /**
     * 电子邮箱
     */
    @javax.validation.constraints.NotNull(message = "email 电子邮箱 为必填项")
    @Size(max = 255, message = "email 不能大于 255 位")
    @javax.validation.constraints.Email(message = "email不合法，请输入正确的邮箱地址")
    private java.lang.String email;

    /**
     * 联系电话
     */
    @javax.validation.constraints.NotNull(message = "phone 联系电话 为必填项")
    @Size(max = 20, message = "phone 不能大于 20 位")
    private java.lang.String phone;

    /**
     * 在线状态  1-在线 0-离线
     */
    private java.lang.Boolean onlineStatus;

    /**
     * 学生信息
     */
    @Transient
    private Student student;

    /**
     * 教师信息
     */
    @Transient
    private Teacher teacher;
}