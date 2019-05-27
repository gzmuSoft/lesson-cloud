package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@Cacheable
@ToString(callSuper = true)
@Table(name = "knowledge")
@Entity(name = "knowledge")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "knowledge", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
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
}