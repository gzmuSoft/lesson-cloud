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
 * exam_rule
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:56
 */
@Data
@Cacheable
@Table(name = "exam_rule")
@Entity(name = "exam_rule")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "exam_rule", usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExamRule extends BaseEntity implements Serializable {

    /**
     * 考试编号
     */
    private java.lang.Long examId;

    /**
     * 题型（单项选择题、多项选择题、判断题、填空题、编程题）
     */
    private java.lang.String questionType;

    /**
     * 题量
     */
    private java.lang.Integer questionCount;

    /**
     * 起始难度系数
     */
    private java.lang.Float startDifficultRate;

    /**
     * 终止难度系数
     */
    private java.lang.Float endDifficultRate;

    /**
     * 每题分值
     */
    private java.lang.Float eachValue;
}