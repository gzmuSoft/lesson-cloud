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
import java.io.Serializable;


/**
 * exam
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:05:30
 */
@Data
@Cacheable
@Table(name = "exam")
@Entity(name = "exam")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "exam", usage = CacheConcurrencyStrategy.READ_WRITE )
public class Exam extends BaseEntity implements Serializable {

    /**
     * 开始时间
     */
    private java.time.LocalDateTime startTime;

    /**
     * 结束时间
     */
    private java.time.LocalDateTime endTime;

    /**
     * 考试总用时限制（正数，如：60分钟）
     */
    private java.lang.Integer totalUseTime;

    /**
     * 满分分值
     */
    private java.lang.Float totalScore;

    /**
     * 课程编号
     */
    private java.lang.Long courseId;

    /**
     * 参与考试的班级id列表，以分号作为分隔符
     */
    private java.lang.String classIds;

    /**
     * 可以考试的次数限制（正数，0代表可以无限次考试）
     */
    private java.lang.Integer allowTimes;
}