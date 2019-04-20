package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;


import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * single_sel
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-20 0:08:37
 */
@Data
@Cacheable
@Table(name = "single_sel")
@Entity(name = "single_sel")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Cache(region = "single_sel", usage = CacheConcurrencyStrategy.READ_WRITE )
public class SingleSel extends BaseEntity implements Serializable {

    /**
     * 难度系数，介于0~1之间
     */
    private java.lang.Float difficultRate;

    /**
     * 参考答案对应的选项在此题选项列表中的顺序
     */
    private java.lang.Short answer;

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