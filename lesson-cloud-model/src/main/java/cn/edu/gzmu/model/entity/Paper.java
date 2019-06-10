package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import cn.edu.gzmu.model.annoection.FieldRepository;
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
import java.util.List;

/**
 * paper
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-27 10:59:08
 */
@Data
@Cacheable
@ToString(callSuper = true)
@Table(name = "paper")
@Entity(name = "paper")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "paper", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
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
    @Size(max = 1024, message = "singleSelOptionIds 不能大于 1024 位")
    private java.lang.String singleSelOptionIds;

    /**
     * 多项选择题id列表
     */
    @Size(max = 512, message = "multiSelIds 不能大于 512 位")
    private java.lang.String multiSelIds;

    /**
     * 多项选择题选项乱序之后的顺序列表（以分号作为分隔符，一个题的多个选项以逗号作为分隔符）
     */
    @Size(max = 1024, message = "multiSelOptionIds 不能大于 1024 位")
    private java.lang.String multiSelOptionIds;

    /**
     * 判断题id列表
     */
    @Size(max = 512, message = "judgementIds 不能大于 512 位")
    private java.lang.String judgementIds;

    /**
     * 判断题选项乱序之后的顺序列表（以分号作为分隔符，一个题的多个选项以逗号作为分隔符）
     */
    @Size(max = 1024, message = "judgementOptionIds 不能大于 1024 位")
    private java.lang.String judgementOptionIds;

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
    private List<SingleSel> singleSel;

    @Transient
    private List<MultiSel> multiSel;

    @Transient
    private List<Judgement> judgement;

    @Transient
    private List<Essay> essay;

    @Transient
    private List<Program> program;

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