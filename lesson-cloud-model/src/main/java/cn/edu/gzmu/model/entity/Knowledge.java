package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * knowledge
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-27 10:59:08
 */
@Data
@ToString(callSuper = true)
@Table(name = "knowledge")
@Entity(name = "knowledge")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Knowledge extends BaseEntity implements Serializable {

    /**
     * 内容简介
     */
    @Size(max = 1024, message = "intro 不能大于 1024 位")
    private java.lang.String intro;

    /**
     * 0，代表无上级，即：课程的顶层知识点
     */
    @javax.validation.constraints.NotNull(message = "parentId 0，代表无上级，即：课程的顶层知识点 为必填项")
    private java.lang.Long parentId;

    /**
     * 课程编号
     */
    @javax.validation.constraints.NotNull(message = "courseId 课程编号 为必填项")
    private java.lang.Long courseId;

    /**
     * 章节编号
     */
    @javax.validation.constraints.NotNull(message = "sectionId 章节编号 为必填项")
    private java.lang.Long sectionId;

    /**
     * 课程信息
     */
    @Transient
    private Course course;

    /**
     * 章节信息
     */
    @Transient
    private Section section;
    /*
    *父级信息
    * */
    @Transient
    private Knowledge parent;
}