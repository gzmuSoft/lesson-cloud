package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
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
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * semester
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-20 11:34:29
 */
@Data
@Cacheable
@ToString(callSuper = true)
@Table(name = "semester")
@Entity(name = "semester")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "semester", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Semester extends BaseEntity implements Serializable {

    /**
     * 学校编号
     */
    @javax.validation.constraints.NotNull(message = "schoolId 为必填项")
    private java.lang.Long schoolId;

    /**
     * 起始日期
     */
    private java.time.LocalDate startDate;

    /**
     * 结束日期
     */
    private java.time.LocalDate endDate;
}