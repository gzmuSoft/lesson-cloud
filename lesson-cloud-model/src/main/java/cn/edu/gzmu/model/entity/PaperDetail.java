package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * paper_detail
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-27 10:59:08
 */
@Data
@ToString(callSuper = true)
@Table(name = "paper_detail")
@Entity(name = "paper_detail")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PaperDetail extends BaseEntity implements Serializable {

    /**
     * 试卷编号
     */
    private java.lang.Long paperId;

    /**
     * 问题编号
     */
    private java.lang.Long questionId;

    /**
     * 参考答案（仅记录单项、多项、判断题这些客观题的参考答案，其他题的答案直接从题库中读取）
     */
    @Size(max = 255, message = "refAnswer 不能大于 255 位")
    private java.lang.String refAnswer;

    /**
     * 用户答案
     */
    @Size(max = 2048, message = "userAnswer 不能大于 2048 位")
    private java.lang.String userAnswer;

    /**
     * 原始分值
     */
    private java.lang.Float orgValue;

    /**
     * 用户得分
     */
    private java.lang.Float obtainValue;
}