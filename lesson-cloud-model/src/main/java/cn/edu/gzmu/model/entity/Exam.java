package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import com.google.common.base.Splitter;
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
import java.util.stream.Collectors;

/**
 * exam
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-27 10:59:08
 */
@Data
@ToString(callSuper = true)
@Table(name = "exam")
@Entity(name = "exam")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Exam extends BaseEntity implements Serializable {

    /**
     * 开始时间
     */
    @javax.validation.constraints.NotNull(message = "开始时间不能为空")
    private java.time.LocalDateTime startTime;

    /**
     * 结束时间
     */
    @javax.validation.constraints.NotNull(message = "结束时间不能为空")
    private java.time.LocalDateTime endTime;

    /**
     * 考试总用时限制（正数，如：60分钟）
     */
    @javax.validation.constraints.NotNull(message = "限时时间不能为空")
    private java.lang.Integer totalUseTime;

    /**
     * 满分分值
     */
    private java.lang.Float totalScore;

    /**
     * 课程编号
     */
    @javax.validation.constraints.NotNull(message = "课程编号不能为空")
    private java.lang.Long courseId;

    /**
     * 是否发布
     */
    @javax.validation.constraints.NotNull(message = "是否发布不能为空")
    private java.lang.Boolean isPublish;

    /**
     * 出题类型：1指定题目、2顺序练习、3指定范围、4随机抽题
     */
    @javax.validation.constraints.NotNull(message = "出题类型不能为空")
    private java.lang.Integer method;

    /**
     * 成绩公布时间
     */
    @javax.validation.constraints.NotNull(message = "成绩公布时间不能为空")
    private java.time.LocalDateTime publicationTime;

    /**
     * 参与考试的班级id列表，以分号作为分隔符
     */
    @Size(max = 512, message = "logicClassIds 不能大于 512 位")
    private java.lang.String logicClassIds;

    /**
     * 可以考试的次数限制（正数，0代表可以无限次考试）
     */
    @javax.validation.constraints.NotNull(message = "可考试次数不能为空")
    private java.lang.Integer allowTimes;

    /**
     * 课程实体
     */
    @Transient
    private Course course;

    /**
     * 班级列表
     */
    @Transient
    private List<LogicClass> logicClasses;

    public List<Long> getLogicClassIds() {
        return Splitter.on(",").splitToList(logicClassIds)
                .stream().map(Long::parseLong)
                .collect(Collectors.toList());
    }
}