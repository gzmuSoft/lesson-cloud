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
 * course
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-27 10:59:08
 */
@Data
@Cacheable
@ToString(callSuper = true)
@Table(name = "course")
@Entity(name = "course")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "course", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Course extends BaseEntity implements Serializable {

    /**
     * 基础学时
     */
    @javax.validation.constraints.NotNull(message = "period 基础学时 为必填项")
    private java.lang.Short period;

    /**
     * 基础学分
     */
    @javax.validation.constraints.NotNull(message = "credit 基础学分 为必填项")
    private java.lang.Float credit;

    /**
     * 课程性质
     */
    @javax.validation.constraints.NotNull(message = "type 课程性质 为必填项")
    @Size(max = 255, message = "type 不能大于 255 位")
    private java.lang.String type;
}