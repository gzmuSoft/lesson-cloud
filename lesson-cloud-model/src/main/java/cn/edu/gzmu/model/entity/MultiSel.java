package cn.edu.gzmu.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * multi_sel
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-10 14:10:58
 */
@Data
@Entity
@Table(name = "multi_sel")
@EqualsAndHashCode(callSuper = true)
public class MultiSel extends cn.edu.gzmu.model.BaseEntity {

    /**
     * 难度系数，介于0~1之间
     */
    private java.lang.Float difficultRate;

    /**
     * 参考答案对应的选项在此题选项列表中的顺序，多个选项的以英文逗号分隔
     */
    private java.lang.String answer;

    /**
     * 答案解析
     */
    private java.lang.String explanation;

    /**
     * 课程编号
     */
    private java.lang.Long courseId;

    /**
     * 章节编号
     */
    private java.lang.Long sectionId;

    /**
     * 知识点编号
     */
    private java.lang.Long knowledgeId;
}