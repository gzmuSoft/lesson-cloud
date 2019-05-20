package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
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
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * single_sel
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-20 11:34:29
 */
@Data
@Cacheable
@ToString(callSuper = true)
@Table(name = "single_sel")
@Entity(name = "single_sel")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "single_sel", usage = CacheConcurrencyStrategy.READ_WRITE)
public class SingleSel extends BaseEntity implements Serializable {

    /**
     * 难度系数，介于0~1之间
     */
    @javax.validation.constraints.NotNull(message = "difficultRate 为必填项")
    private java.lang.Float difficultRate;

    /**
     * 参考答案对应的选项在此题选项列表中的顺序
     */
    @javax.validation.constraints.NotNull(message = "answer 为必填项")
    private java.lang.Short answer;

    /**
     * 答案解析
     */
    @Size(max = 2048, message = "explanation 不能大于 2048 位")
    private java.lang.String explanation;

    /**
     * 课程编号
     */
    @javax.validation.constraints.NotNull(message = "courseId 为必填项")
    private java.lang.Long courseId;

    /**
     * 章节编号
     */
    @javax.validation.constraints.NotNull(message = "sectionId 为必填项")
    private java.lang.Long sectionId;

    /**
     * 知识点编号
     */
    @javax.validation.constraints.NotNull(message = "knowledgeId 为必填项")
    private java.lang.Long knowledgeId;
}