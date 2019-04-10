package cn.edu.gzmu.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * knowledge
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-10 14:10:58
 */
@Data
@Entity
@Table(name = "knowledge")
@EqualsAndHashCode(callSuper = true)
public class Knowledge extends cn.edu.gzmu.model.BaseEntity {

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