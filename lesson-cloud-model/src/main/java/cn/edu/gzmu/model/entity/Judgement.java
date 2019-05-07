package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;


import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * judgement
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:56
 */
@Data
@Cacheable
@Table(name = "judgement")
@Entity(name = "judgement")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "judgement", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Judgement extends BaseEntity implements Serializable {

    /**
     * 难度系数，介于0~1之间
     */
    private java.lang.Float difficultRate;

    /**
     * 参考答案（1：正确，0：错误）
     */
    private java.lang.Byte answer;

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