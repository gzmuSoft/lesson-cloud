package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import cn.edu.gzmu.model.constant.QuestionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Question.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/19 下午3:49
 */
@Data
@ToString(callSuper = true)
@Table(name = "question")
@Entity(name = "question")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Question extends BaseEntity implements Serializable {

    /**
     * 难度系数，介于0~1之间
     */
    @javax.validation.constraints.NotNull(message = "difficultRate 难度系数，介于0-100之间 为必填项")
    private java.lang.Integer difficultRate;

    /**
     * 参考答案
     */
    @javax.validation.constraints.NotNull(message = "answer 参考答案 为必填项")
    @Size(max = 2048, message = "answer 不能大于 2048 位")
    private java.lang.String answer;

    /**
     * 答案解析
     */
    @Size(max = 2048, message = "explanation 不能大于 2048 位")
    private java.lang.String explanation;

    /**
     * 是否公开
     */
    private java.lang.Boolean isPublic = false;

    /**
     * 0.单选题 1.多选题 2.判断题 3.填空题 4.问答题 5.编程
     */
    @Enumerated
    private QuestionType type;

}
