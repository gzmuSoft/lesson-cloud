package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * sys_res
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-27 10:59:09
 */
@Data
@ToString(callSuper = true)
@Table(name = "sys_res")
@Entity(name = "sys_res")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysRes extends BaseEntity implements Serializable {

    /**
     * 描述
     */
    @Size(max = 1024, message = "des 不能大于 1024 位")
    private java.lang.String des;

    /**
     * url 匹配
     */
    @javax.validation.constraints.NotNull(message = "matchUrl url 匹配 为必填项")
    @Size(max = 512, message = "matchUrl 不能大于 512 位")
    private java.lang.String matchUrl;

    /**
     * 允许使用的方法：GET、POST、PUT、PATCH、DELETE、ALL
     */
    @Size(max = 50, message = "method 不能大于 50 位")
    private java.lang.String method;

}