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
import java.io.Serializable;


/**
 * semester
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-20 0:08:37
 */
@Data
@Cacheable
@Table(name = "semester")
@Entity(name = "semester")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Cache(region = "semester", usage = CacheConcurrencyStrategy.READ_WRITE )
public class Semester extends BaseEntity implements Serializable {

    /**
     * 学校编号
     */
    private java.lang.Long schoolId;

    /**
     * 起始日期
     */
    private java.time.LocalDate startDate;

    /**
     * 结束日期
     */
    private java.time.LocalDate endDate;

    /**
     * 创建用户名称
     */
    private java.lang.String createName;
}