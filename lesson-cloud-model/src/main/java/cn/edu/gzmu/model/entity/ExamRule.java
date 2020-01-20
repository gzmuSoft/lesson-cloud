package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import cn.edu.gzmu.model.constant.QuestionType;
import com.alibaba.fastjson.JSONObject;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * exam_rule
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-27 10:59:08
 */
@Data
@ToString(callSuper = true)
@Table(name = "exam_rule")
@Entity(name = "exam_rule")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class ExamRule extends BaseEntity implements Serializable {

    /**
     * 考试编号
     */
    private java.lang.Long examId;

    /**
     * 题型（单项选择题、多项选择题、判断题、填空题、编程题）
     */
    @Enumerated
    @javax.validation.constraints.NotNull(message = "type 类型, 为必填项")
    private QuestionType questionType;

    /**
     * 题量
     */
    private java.lang.Integer questionCount;

    /**
     * 起始难度系数
     */
    private java.lang.Integer startDifficultRate;

    /**
     * 终止难度系数
     */
    private java.lang.Integer endDifficultRate;

    /**
     * 每题分值
     */
    @javax.validation.constraints.NotNull(message = "eachValue 每题分值 为必填项")
    private java.lang.Float eachValue;

    @Type(type = "json")
    private JSONObject ruleDetail;

    /**
     * 考试编号
     */
    @Transient
    private Exam exam;
}