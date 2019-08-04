package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * exam_history
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-27 10:59:08
 */
@Data
@Cacheable
@ToString(callSuper = true)
@Table(name = "exam_history")
@Entity(name = "exam_history")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "exam_history", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class ExamHistory extends BaseEntity implements Serializable {

    /**
     * 考试编号
     */
    private java.lang.Long examId;

    /**
     * 学生编号
     */
    private java.lang.Long studentId;

    /**
     * 学生信息
     */
    @Transient
    private Student student;

    /**
     * 最高得分
     */
    private java.lang.Float maxScore;

    /**
     * 得分最高的试卷编号
     */
    private java.lang.Long paperId;

    /**
     * 得分最高的试卷编号
     */
    @Transient
    private Paper paper;

    /**
     * 得分最高的考试的开始时间
     */
    private java.time.LocalDateTime examTime;

    /**
     * 已考次数（生成paper时就计数+1）
     */
    private java.lang.Integer times;



}