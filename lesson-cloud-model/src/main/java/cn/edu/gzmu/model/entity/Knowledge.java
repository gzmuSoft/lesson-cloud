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

/**
 * knowledge
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-19 22:08:04
 */
@Data
@Cacheable
@Table(name = "knowledge")
@Entity(name = "knowledge")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Cache(region = "knowledge", usage = CacheConcurrencyStrategy.READ_WRITE )
public class Knowledge extends BaseEntity {

    /**
     * 内容简介
     */
    private java.lang.String intro;

    /**
     * 0，代表无上级，即：课程的顶层知识点
     */
    private java.lang.Long parentId;

    /**
     * 课程编号
     */
    private java.lang.Long courseId;

    /**
     * 章节编号
     */
    private java.lang.Long sectionId;
}