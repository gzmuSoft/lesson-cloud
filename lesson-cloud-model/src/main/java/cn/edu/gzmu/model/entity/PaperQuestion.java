package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
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
@Table(name = "paper_question")
@Entity(name = "paper_question")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class PaperQuestion extends BaseEntity implements Serializable {

    /**
     * 章节编号
     */
    @javax.validation.constraints.NotNull(message = "sectionId 章节编号 为必填项")
    private java.lang.Long questionId;

    /**
     * 知识点编号
     */
    @javax.validation.constraints.NotNull(message = "paperId  试卷id 为必填项")
    private java.lang.Long paperId;

    @Type(type = "json")
    private JSONObject questionDetail;

}
