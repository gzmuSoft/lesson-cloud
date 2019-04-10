package cn.edu.gzmu.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * section
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-10 14:10:59
 */
@Data
@Entity
@Table(name = "section")
@EqualsAndHashCode(callSuper = true)
public class Section extends cn.edu.gzmu.model.BaseEntity {

    /**
     * 内容简介
     */
    private java.lang.String intro;

    /**
     * 课程编号
     */
    private java.lang.Long courseId;

    /**
     * 0，代表无上级，即：课程的第一个章节
     */
    private java.lang.Long parentId;

    /**
     * 类型，0：章，1：节
     */
    private java.lang.Boolean type;
}