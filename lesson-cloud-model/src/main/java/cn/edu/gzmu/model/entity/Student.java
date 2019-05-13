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
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * student
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-13 22:20:23
 */
@Data
@Cacheable
@Table(name = "student")
@Entity(name = "student")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "student", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Student extends BaseEntity implements Serializable {

    /**
     * 用户编号
     */
    private java.lang.Long userId;

    /**
     * 学校编号
     */
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
     * 班级编号
     */
    private java.lang.Long classId;

    /**
     * 性别
     */
    @Size(max = 255, message = "gender 不能大于 255 位")
    private java.lang.String gender;

    /**
     * 出生日期
     */
    @javax.validation.constraints.Past
    private java.time.LocalDate birthday;

    /**
     * 入校时间
     */
    private java.time.LocalDate enterDate;

    /**
     * 最后学历
     */
    @Size(max = 255, message = "academic 不能大于 255 位")
    private java.lang.String academic;

    /**
     * 最后学历毕业时间
     */
    private java.time.LocalDate graduationDate;

    /**
     * 最后学历毕业院校
     */
    @Size(max = 255, message = "graduateInstitution 不能大于 255 位")
    private java.lang.String graduateInstitution;

    /**
     * 最后学历所学专业（若最后学历是高中，则不需要填写
若最后学历是大专，则需要填写）
     */
    @Size(max = 255, message = "originalMajor 不能大于 255 位")
    private java.lang.String originalMajor;

    /**
     * 个人简历
     */
    @Size(max = 2048, message = "resume 不能大于 2048 位")
    private java.lang.String resume;
}