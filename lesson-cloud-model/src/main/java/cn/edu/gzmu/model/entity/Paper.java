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
 * paper
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:05:30
 */
@Data
@Cacheable
@Table(name = "paper")
@Entity(name = "paper")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "paper", usage = CacheConcurrencyStrategy.READ_WRITE )
public class Paper extends BaseEntity implements Serializable {

    /**
     * 考试编号
     */
    private java.lang.Long examId;

    /**
     * 学生编号
     */
    private java.lang.Long studentId;

    /**
     * 考试开始时间
     */
    private java.time.LocalDateTime startTime;

    /**
     * 考试交卷时间
     */
    private java.time.LocalDateTime submitTime;

    /**
     * 考试成绩
     */
    private java.lang.Float score;

    /**
     * 单项选择题id列表
     */
    private java.lang.String singleSelIds;

    /**
     * 单项选择题选项乱序之后的顺序列表（以分号作为分隔符，一个题的多个选项以逗号作为分隔符）
     */
    private java.lang.String singleSelOpts;

    /**
     * 多项选择题id列表
     */
    private java.lang.String multiSelIds;

    /**
     * 多项选择题选项乱序之后的顺序列表（以分号作为分隔符，一个题的多个选项以逗号作为分隔符）
     */
    private java.lang.String multiSelOpts;

    /**
     * 判断题id列表
     */
    private java.lang.String judgementIds;

    /**
     * 判断题选项乱序之后的顺序列表（以分号作为分隔符，一个题的多个选项以逗号作为分隔符）
     */
    private java.lang.String judgementOpts;

    /**
     * 问答题id列表
     */
    private java.lang.String essayIds;

    /**
     * 编程题id列表
     */
    private java.lang.String programIds;
}