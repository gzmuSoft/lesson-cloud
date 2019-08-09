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
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * logic_class
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-27 10:59:08
 *
 * @author Japoul
 * @date 2019-8-4 23:33:57
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
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class LogicClass extends BaseEntity implements Serializable {

    /**
     * 0：物理班级(class_id值为实体班级id)，1：学生个体(student_id为学生实体id)
     */
    private java.lang.Boolean type;

    /**
     * 学校编号
     */
    @javax.validation.constraints.NotNull(message = "schoolId 学校编号 为必填项")
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
    private java.lang.Long classesId;

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

    /**
     * 学生实体类, type为1值，本字段值才有效
     */
    @Transient
    private Student student;

    /**
     * 教师实体类
     */
    @Transient
    private Teacher teacher;

    /**
     * 学期实体类
     */
    @Transient
    private Semester semester;

    /**
     * 课程实体类
     */
    @Transient
    private Course course;

    /**
     * 学校
     */
    @Transient
    private SysData school;

    /**
     * 学院
     */
    @Transient
    private SysData college;

    /**
     * 系部
     */
    @Transient
    private SysData dep;

    /**
     * 专业
     */
    @Transient
    private SysData specialty;

    /**
     * 实体班级，type为0值，本字段值才有效
     */
    @Transient
    private SysData classes;

    /**
     * 逻辑班级上课时间及地点
     */
    @Transient
    List<CourseTimetableLocation> courseTimetableLocationList;

}