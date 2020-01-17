package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * sys_menu.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/17 下午2:09
 */
@Data
@ToString(callSuper = true)
@Table(name = "sys_menu")
@Entity(name = "sys_menu")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysMenu extends BaseEntity implements Serializable  {

    /**
     * 描述
     */
    private java.lang.String des;

    /**
     * 路由名称
     */
    private java.lang.String routeName;

    /**
     * 图标
     */
    private java.lang.String icon;

}
