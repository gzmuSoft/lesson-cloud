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
 * course
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-11 17:24:40
 */
@Data
@Cacheable
@Table(name = "course")
@Entity(name = "course")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE )
public class Course extends BaseEntity {

    /**
     * 基础学时
     */
    private java.lang.Short period;

    /**
     * 基础学分
     */
    private java.lang.Float credit;

    /**
     * 课程性质
     */
    private java.lang.String type;
}