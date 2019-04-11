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
 * sys_data
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-11 17:24:40
 */
@Data
@Cacheable
@Table(name = "sys_data")
@Entity(name = "sys_data")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE )
public class SysData extends BaseEntity {

    /**
     * 0，代表无上级，即：学校
     */
    private java.lang.Long parentId;

    /**
     * 简介
     */
    private java.lang.String brief;

    /**
     * 0：学校，1：学院，2：系部，3：专业，4：班级
     */
    private java.lang.Integer type;
}