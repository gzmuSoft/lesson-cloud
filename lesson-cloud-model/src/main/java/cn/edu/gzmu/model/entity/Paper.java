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
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * paper
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-8 16:03:14
 */
@Data
@Cacheable
@Table(name = "paper")
@Entity(name = "paper")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "paper", usage = CacheConcurrencyStrategy.READ_WRITE)
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
    @Size(max = 512, message = "singleSelIds 不能大于 512 位")
    private java.lang.String singleSelIds;

    /**
     * 单项选择题选项乱序之后的顺序列表（以分号作为分隔符，一个题的多个选项以逗号作为分隔符）
     */
    @Size(max = 1024, message = "singleSelOpts 不能大于 1024 位")
    private java.lang.String singleSelOpts;

    /**
     * 多项选择题id列表
     */
    @Size(max = 512, message = "multiSelIds 不能大于 512 位")
    private java.lang.String multiSelIds;

    /**
     * 多项选择题选项乱序之后的顺序列表（以分号作为分隔符，一个题的多个选项以逗号作为分隔符）
     */
    @Size(max = 1024, message = "multiSelOpts 不能大于 1024 位")
    private java.lang.String multiSelOpts;

    /**
     * 判断题id列表
     */
    @Size(max = 512, message = "judgementIds 不能大于 512 位")
    private java.lang.String judgementIds;

    /**
     * 判断题选项乱序之后的顺序列表（以分号作为分隔符，一个题的多个选项以逗号作为分隔符）
     */
    @Size(max = 1024, message = "judgementOpts 不能大于 1024 位")
    private java.lang.String judgementOpts;

    /**
     * 问答题id列表
     */
    @Size(max = 512, message = "essayIds 不能大于 512 位")
    private java.lang.String essayIds;

    /**
     * 编程题id列表
     */
    @Size(max = 512, message = "programIds 不能大于 512 位")
    private java.lang.String programIds;
}