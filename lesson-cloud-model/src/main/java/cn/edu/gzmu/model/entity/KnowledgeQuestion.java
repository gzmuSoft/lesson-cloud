package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/19 下午3:53
 */
@Data
@ToString(callSuper = true)
@Table(name = "knowledge_question")
@Entity(name = "knowledge_question")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class KnowledgeQuestion extends BaseEntity implements Serializable {

    /**
     * 知识点编号
     */
    @javax.validation.constraints.NotNull(message = "questionId 知识点编号 为必填项")
    private java.lang.Long questionId;

    /**
     * 知识点编号
     */
    @javax.validation.constraints.NotNull(message = "knowledgeId 知识点编号 为必填项")
    private java.lang.Long knowledgeId;

}
