package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * judgement
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-27 10:59:08
 */
@Data
@ToString(callSuper = true)
@Table(name = "judgement")
@Entity(name = "judgement")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Judgement extends BaseEntity implements Serializable {

    /**
     * 难度系数，介于0~1之间
     */
    @NotNull(message = "difficultRate 难度系数，介于0~1之间 为必填项")
    private Float difficultRate;

    /**
     * 参考答案（1：正确，0：错误）
     */
    @NotNull(message = "answer 参考答案（1：正确，0：错误） 为必填项")
    private Byte answer;

    /**
     * 答案解析
     */
    @Size(max = 2048, message = "explanation 不能大于 2048 位")
    private String explanation;

    /**
     * 课程编号
     */
    @NotNull(message = "courseId 课程编号 为必填项")
    private Long courseId;

    /**
     * 章节编号
     */
    @NotNull(message = "sectionId 章节编号 为必填项")
    private Long sectionId;

    /**
     * 知识点编号
     */
    @NotNull(message = "knowledgeId 知识点编号 为必填项")
    private Long knowledgeId;

    /**
     * 课程实体
     * */
    @Transient
    private Course course;

    /**
     * 章节实体
     * */
    @Transient
    private Section section;

    /**
     * 知识点编号
     * */
    @Transient
    private Knowledge knowledge;

    /**
     * 是否公开
     */
    private Boolean isPublic = false;

    /**
     * 默认值
     */
    private String remark = "JUDGEMENT";
}