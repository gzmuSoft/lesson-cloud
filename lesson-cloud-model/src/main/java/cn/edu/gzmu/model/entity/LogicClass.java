package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
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
 * logic_class
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-20 11:34:28
 */
@Data
@Cacheable
@ToString(callSuper = true)
@Table(name = "logic_class")
@Entity(name = "logic_class")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "logic_class", usage = CacheConcurrencyStrategy.READ_WRITE)
public class LogicClass extends BaseEntity implements Serializable {

    /**
     * 0：物理班级(class_id值为实体班级id)，1：学生个体(student_id为学生实体id)
     */
    private java.lang.Byte type;

    /**
     * 学校编号
     */
    @javax.validation.constraints.NotNull(message = "schoolId 为必填项")
    private java.lang.Long schoolId;

    /**
     * 学院编号
     */
    private java.lang.Long collegeId;

    /**
     * 系部编号
     */
    private java.lang.Long depId;

    /**
     * 专业编号
     */
    private java.lang.Long specialtyId;

    /**
     * 实体班级id，type为0值，本字段值才有效
     */
    private java.lang.Long classId;

    /**
     * 学生实体id，type为1值，本字段值才有效
     */
    private java.lang.Long studentId;

    /**
     * 教师编号
     */
    private java.lang.Long teacherId;

    /**
     * 学期编号
     */
    private java.lang.Long semesterId;

    /**
     * 课程编号
     */
    private java.lang.Long courseId;

    /**
     * 学时
     */
    private java.lang.Short period;

    /**
     * 学分
     */
    private java.lang.Float credit;

    /**
     * 课程性质
     */
    @Size(max = 255, message = "courseType 不能大于 255 位")
    private java.lang.String courseType;
}