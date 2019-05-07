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
 * teacher
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Data
@Cacheable
@Table(name = "teacher")
@Entity(name = "teacher")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "teacher", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Teacher extends BaseEntity implements Serializable {

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
     * 性别
     */
    private java.lang.String gender;

    /**
     * 出生日期
     */
    private java.time.LocalDate birthday;

    /**
     * 民族
     */
    private java.lang.String nation;

    /**
     * 学位
     */
    private java.lang.String degree;

    /**
     * 最后学历
     */
    private java.lang.String academic;

    /**
     * 最后学历毕业时间
     */
    private java.time.LocalDate graduationDate;

    /**
     * 最后学历所学专业
     */
    private java.lang.String major;

    /**
     * 最后学历毕业院校
     */
    private java.lang.String graduateInstitution;

    /**
     * 主要研究方向
     */
    private java.lang.String majorResearch;

    /**
     * 个人简历
     */
    private java.lang.String resume;

    /**
     * 参加工作时间
     */
    private java.time.LocalDate workDate;

    /**
     * 职称
     */
    private java.lang.String profTitle;

    /**
     * 职称评定时间
     */
    private java.time.LocalDate profTitleAssDate;

    /**
     * 是否学术学科带头人
     */
    private java.lang.Byte isAcademicLeader;

    /**
     * 所属学科门类
     */
    private java.lang.String subjectCategory;

    /**
     * 身份证号码
     */
    private java.lang.String idNumber;

    /**
     * 联系电话
     */
    private java.lang.String phone;

    /**
     * 邮箱
     */
    private java.lang.String email;
}