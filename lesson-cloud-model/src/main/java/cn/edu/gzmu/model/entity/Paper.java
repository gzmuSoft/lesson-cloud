package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import cn.edu.gzmu.model.annoection.FieldRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * paper
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-27 10:59:08
 */
@Data
@ToString(callSuper = true)
@Table(name = "paper")
@Entity(name = "paper")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
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
     * 测试
     */
    @Transient
    private Exam exam;

    /**
     * 学生
     */
    @Transient
    private Student student;

    @Transient
    @FieldRepository("selOptionsRepository")
    private List<SelOptions> singleSelOption;

    @Transient
    @FieldRepository("selOptionsRepository")
    private List<SelOptions> multiSelOption;

    @Transient
    @FieldRepository("selOptionsRepository")
    private List<SelOptions> judgementOption;

}